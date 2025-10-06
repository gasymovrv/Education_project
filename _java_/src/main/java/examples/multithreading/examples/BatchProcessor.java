package examples.multithreading.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Batch Job Processor.
 *
 * <p>Processes a stream of small tasks concurrently, but persists results
 * in batches of fixed size (e.g. 1000) to reduce downstream load such as
 * database overhead.
 *
 * <h2>Production relevance</h2>
 * <ul>
 *   <li>Batching is a classic optimization in data pipelines.</li>
 *   <li>Used in analytics, telemetry, and event ingestion services.</li>
 * </ul>
 *
 * <h2>Hints</h2>
 * <ul>
 *   <li>Submit work units via virtual threads for scalability.</li>
 *   <li>Aggregate results using a {@link java.util.concurrent.BlockingQueue}.</li>
 *   <li>Synchronize batches with a {@link java.util.concurrent.Phaser} or manual flushing logic.</li>
 * </ul>
 */
public class BatchProcessor {
    static final int BATCH_SIZE = 1000;

    record Job(int value) implements Comparable<Job> {
        @Override
        public int compareTo(Job o) {
            return value - o.value;
        }
    }

    static class Batcher implements AutoCloseable {
        private final BlockingQueue<Job> queue = new LinkedBlockingQueue<>();
        private volatile boolean running = true;
        private final Thread consumer;

        Batcher() {
            consumer = Thread.ofVirtual().name("batcher").start(() -> {
                List<Job> batch = new ArrayList<>(BATCH_SIZE);
                try {
                    while (running || !queue.isEmpty()) {
                        Job j = queue.poll(200, TimeUnit.MILLISECONDS);
                        if (j != null) {
                            batch.add(j);
                            if (batch.size() >= BATCH_SIZE) {
                                persist(batch);
                                batch.clear();
                            }
                        }
                    }
                    if (!batch.isEmpty()) persist(batch);
                } catch (InterruptedException ignored) {
                }
            });
        }

        void submit(Job j) {
            queue.offer(j);
        }

        private void persist(List<Job> batch) {
            // Simulate DB commit
            var sortedBatch = batch.stream().sorted().toList();
            int first = sortedBatch.stream().min(Job::compareTo).get().value();
            int last = sortedBatch.stream().max(Job::compareTo).get().value();
            System.out.printf("Persisting batch where min=%d, max=%d, size=%d\n", first, last, batch.size());
        }

        @Override
        public void close() throws InterruptedException {
            running = false;
            consumer.join();
        }
    }

    public static void main(String[] args) throws Exception {
        try (Batcher batcher = new Batcher();
             ExecutorService exec = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10_000; i++) {
                int v = i;
                exec.submit(() -> batcher.submit(new Job(v)));
            }
        }
        System.out.println("Completed.");
    }
}
