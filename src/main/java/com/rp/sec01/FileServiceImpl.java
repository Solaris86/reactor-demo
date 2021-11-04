
/*
 * Classname : com.rp.sec01.FileServiceImpl
 *
 * Created on: 04 Nov 2021
 *
 * Copyright (c) 2000- Global Payments, Ltd.
 * Global Payments, The Observatory, 7-11 Sir John Rogerson's Quay, Dublin 2, Ireland
 *
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Global Payments, Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Global Payments.
 *
 */
package com.rp.sec01;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileServiceImpl implements FileService {
    @Override
    public Mono<String> readFile(String fileName) {
        return Mono.fromSupplier(() -> {
            Path path;
            URL url = getClass().getClassLoader().getResource("assignment/sec01/" + fileName);
            if (Objects.isNull(url)) {
                throw new RuntimeException("File not [" + fileName + "] not found");
            }

            try {
                path = Paths.get(url.toURI());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

            try (Stream<String> lines = Files.lines(path)) {
                return lines.collect(Collectors.joining("\n"));
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        });
    }

    @Override
    public Mono<Void> createFile(String fileName, String content) {
        return Mono.fromRunnable(() -> {
            try {
                Files.write(Path.of(fileName), Arrays.asList(content.split("\n")));
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        });
    }

    @Override
    public Mono<Void> deleteFile(String fileName) {
        return Mono.fromRunnable(() -> {
            Path path;
            URL url = getClass().getClassLoader().getResource("assignment/sec01/" + fileName);
            if (Objects.isNull(url)) {
                throw new RuntimeException("File not [" + fileName + "] not found");
            }

            try {
                path = Paths.get(url.toURI());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

            try {
                Files.delete(path);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        });
    }
}
