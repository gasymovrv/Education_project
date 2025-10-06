package examples.multithreading.examples;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Asynchronous Logging Service (Producer–Consumer).
 *
 * <p>A high-throughput logger where multiple producer threads submit
 * log messages, and a single consumer thread writes them to disk.
 * This design reduces contention on disk I/O.
 *
 * <h2>Production relevance</h2>
 * <ul>
 *   <li>Similar to how frameworks like Log4j or Logback implement async appenders.</li>
 *   <li>Separates fast producers from slow consumers (disk or network).</li>
 * </ul>
 *
 * <h2>Hints</h2>
 * <ul>
 *   <li>Use {@link java.util.concurrent.BlockingQueue} to buffer log events.</li>
 *   <li>Experiment with bounded vs. unbounded queues to handle backpressure.</li>
 *   <li>Use a virtual thread for the consumer to avoid blocking platform threads.</li>
 * </ul>
 */
public class AsyncLoggerDemo {

    static class AsyncLogger implements AutoCloseable {
        private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        private final Thread consumer;
        private volatile boolean running = true;

        AsyncLogger(Path path) throws IOException {
            Files.createDirectories(path.getParent());
            consumer = Thread.ofVirtual().name("log-consumer").unstarted(() -> {
                try (BufferedWriter out = Files.newBufferedWriter(path)) {
                    while (running || !queue.isEmpty()) {
                        String line = queue.poll(200, TimeUnit.MILLISECONDS);
                        if (line != null) {
                            out.write(line);
                            out.newLine();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            consumer.start();
        }

        public void log(String msg) {
            queue.offer(OffsetDateTime.now() + " " + msg);
        }

        @Override
        public void close() throws InterruptedException {
            running = false;
            consumer.join();
        }
    }

    public static void main(String[] args) throws Exception {
        try (AsyncLogger logger = new AsyncLogger(Paths.get("logs/app.log"));
             ExecutorService exec = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10_000; i++) {
                int id = i;
                exec.submit(() -> logger.log("msg-" + id));
            }
        }
        System.out.println("Done.");
    }
}
