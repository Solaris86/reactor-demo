package com.rp.sec01;

import reactor.core.publisher.Mono;

public interface FileService {

    Mono<String> readFile(String fileName);

    Mono<Void> createFile(String fileName, String content);

    Mono<Void> deleteFile(String fileName);
}
