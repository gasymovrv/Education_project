package examples.multithreading.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Rate-Limited API Gateway.
 *
 * <p>Implements a simple token-bucket style rate limiter to ensure that
 * outgoing requests do not exceed a maximum throughput (e.g. 10 requests per second).
 *
 * <h2>Production relevance</h2>
 * <ul>
 *   <li>Protects upstream APIs from overloading and enforces SLAs.</li>
 *   <li>Forms the basis of API gateways, proxies, and throttling middleware.</li>
 * </ul>
 *
 * <h2>Hints</h2>
 * <ul>
 *   <li>Use a {@link java.util.concurrent.Semaphore} to model available tokens.</li>
 *   <li>Replenish tokens on a schedule using a {@link java.util.concurrent.ScheduledExecutorService}.</li>
 *   <li>Decide a policy when the limit is hit: delay, reject, or drop requests.</li>
 * </ul>
 */
public class RateLimitedGateway {
    private final Semaphore tokens;
    private final ScheduledExecutorService refill =
            Executors.newSingleThreadScheduledExecutor(r -> Thread.ofPlatform().name("refill").factory().newThread(r));

    public RateLimitedGateway(int permitsPerSecond) {
        this.tokens = new Semaphore(0, true);
        refill.scheduleAtFixedRate(() -> tokens.release(Math.max(0, permitsPerSecond - tokens.availablePermits())),
                0, 1, TimeUnit.SECONDS);
    }

    public <R> R handle(Supplier<R> downstream) throws InterruptedException {
        if (tokens.tryAcquire(100, TimeUnit.MILLISECONDS)) {
            return downstream.get();
        } else {
            // Simulate HTTP 429
            throw new RejectedExecutionException("Rate limit exceeded");
        }
    }

    public void shutdown() {
        refill.shutdownNow();
    }

    // Demo
    public static void main(String[] args) throws Exception {
        RateLimitedGateway gw = new RateLimitedGateway(10);
        try (ExecutorService exec = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 50; i++) {
                int id = i;
                exec.submit(() -> {
                    try {
                        if (id > 15) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        String res = gw.handle(() -> "OK-" + id);
                        System.out.println(res);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                });
            }
        }
        gw.shutdown();
        System.out.println("Done.");
    }
}
