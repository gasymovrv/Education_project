package examples.multithreading.examples;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Concurrent Web Scraper.
 *
 * <p>This program concurrently fetches a list of webpages, extracts their
 * <title> content, and aggregates the results. Each request is executed
 * in its own <b>virtual thread</b>, which is ideal for I/O-bound workloads.
 *
 * <h2>Production relevance</h2>
 * Models a common use case such as:
 * <ul>
 *   <li>Fetching data from multiple APIs (e.g. price aggregators, search engines)</li>
 *   <li>Parallel monitoring of remote services</li>
 * </ul>
 *
 * <h2>Hints</h2>
 * <ul>
 *   <li>Use {@link java.net.http.HttpClient} for non-blocking HTTP requests.</li>
 *   <li>Store results in a thread-safe structure like {@link java.util.concurrent.ConcurrentHashMap}.</li>
 *   <li>If ordering is important, consider {@link java.util.concurrent.ConcurrentSkipListMap}.</li>
 * </ul>
 */

public class WebScraper {
    private static final Pattern TITLE = Pattern.compile("(?is)<title>(.*?)</title>");

    public static void main(String[] args) throws Exception {
        List<String> urls = List.of(
                "https://www.oracle.com",
                "https://openjdk.org",
                "https://www.wikipedia.org"
        );

        Map<String, String> titles = new ConcurrentHashMap<>();

        try (HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
             ExecutorService exec = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<?>> futures = new ArrayList<>();
            for (String url : urls) {
                futures.add(exec.submit(() -> {
                    try {
                        HttpRequest req = HttpRequest.newBuilder(URI.create(url)).timeout(Duration.ofSeconds(10)).build();
                        String body = client.send(req, HttpResponse.BodyHandlers.ofString()).body();
                        String title = extractTitle(body).orElse("(no title)");
                        titles.put(url, title);
                    } catch (Exception e) {
                        titles.put(url, "(error: " + e.getClass().getSimpleName() + ")");
                    }
                }));
            }

            for (Future<?> f : futures) f.get();
        }

        titles.forEach((u, t) -> System.out.println(u + " -> " + t));
    }

    private static Optional<String> extractTitle(String html) {
        Matcher m = TITLE.matcher(html);
        return m.find() ? Optional.of(m.group(1).trim()) : Optional.empty();
    }
}
