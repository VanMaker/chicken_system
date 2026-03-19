package com.njau.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;

public class CloudImageFetcher  {
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .followRedirects(HttpClient.Redirect.NORMAL) // 云端常见 302
            .build();

    public Path downloadTo(String imageUrl, Path outPath) throws IOException, InterruptedException {
        Files.createDirectories(outPath.getParent());

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(imageUrl))
                .timeout(Duration.ofSeconds(30))
                .GET()
                .header("User-Agent", "Mozilla/5.0") // 某些云端会拦默认 UA
                .build();

        HttpResponse<byte[]> resp = client.send(req, HttpResponse.BodyHandlers.ofByteArray());

        if (resp.statusCode() != 200) {
            throw new IOException("HTTP " + resp.statusCode() + " url=" + imageUrl);
        }

        // 可选：检查内容类型，防止下载到 HTML/错误页
        String ct = resp.headers().firstValue("Content-Type").orElse("");
        if (!ct.startsWith("image/")) {
            throw new IOException("Not an image. Content-Type=" + ct + " url=" + imageUrl);
        }

        Files.write(outPath, resp.body(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        return outPath;
    }

}
