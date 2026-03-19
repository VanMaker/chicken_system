package com.njau.utils;

import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtilsExt {

    public FileUtilsExt() {

    }

    /** 删除路径（文件或目录）并重建为空目录 */
    public Path resetDir(String dirPath) throws IOException {
        return resetDir(Paths.get(dirPath));
    }
    /** 删除路径（文件或目录）并重建为空目录 */
    public Path resetDir(Path dir) throws IOException {
        if (dir == null) throw new IllegalArgumentException("dir is null");
        // 1) 删除（如果存在）
        if (Files.exists(dir)) {
            // walk 结果包含自己本身，按深度倒序删除：先删子，再删父
            try (var walk = Files.walk(dir)) {
                walk.sorted(Comparator.reverseOrder())
                        .forEach(p -> {
                            try {
                                Files.deleteIfExists(p);
                            } catch (IOException e) {
                                throw new RuntimeException("Failed to delete: " + p, e);
                            }
                        });
            }
        }

        // 2) 重建空目录
        return Files.createDirectories(dir);
    }

    public Path ensureDir(Path dir) throws IOException {
        if (dir == null) {
            throw new IllegalArgumentException("dir is null");
        }
        if (Files.notExists(dir)) {
            return Files.createDirectories(dir);
        }
        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException("Path exists but is not a directory: " + dir);
        }
        return dir;
    }

    public List<String> listFileNames(Path dir) throws IOException {
        if (dir == null) throw new IllegalArgumentException("dir is null");
        if (Files.notExists(dir)) throw new IllegalArgumentException("dir not exists: " + dir);
        if (!Files.isDirectory(dir)) throw new IllegalArgumentException("not a directory: " + dir);

        try (Stream<Path> s = Files.list(dir)) {
            return s.filter(Files::isRegularFile)
                    .map(p -> p.toAbsolutePath().toString())
                    .collect(Collectors.toList());
        }
    }

    public Path copyToDir(Path fileA, Path dirB) throws IOException {
        if (fileA == null || dirB == null) {
            throw new IllegalArgumentException("fileA/dirB is null");
        }
        if (!Files.exists(fileA) || !Files.isRegularFile(fileA)) {
            throw new IllegalArgumentException("source is not a file: " + fileA);
        }

        Files.createDirectories(dirB); // 目标目录不存在就创建

        Path target = dirB.resolve(fileA.getFileName());
        return Files.move(fileA, target, StandardCopyOption.REPLACE_EXISTING);
    }

    // String 重载：方便你直接传 String
    public Path copyToDir(String fileA, String dirB) throws IOException {
        return copyToDir(Paths.get(fileA), Paths.get(dirB));
    }
}

