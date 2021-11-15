package com.rp.sec03;

import reactor.core.publisher.Flux;

import java.nio.file.Path;

public interface FileReaderService {

    Flux<String> getPublisher(Path path);

}
