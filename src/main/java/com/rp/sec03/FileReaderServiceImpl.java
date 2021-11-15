package com.rp.sec03;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FileReaderServiceImpl implements FileReaderService {

    @Override
    public Flux<String> getPublisher(Path path) {
        return Flux.generate(openReader(path), read(), closeReader());
    }

    private Callable<BufferedReader> openReader(Path path) {
        return () -> Files.newBufferedReader(path);
    }

    private BiFunction<BufferedReader, SynchronousSink<String>, BufferedReader> read() {
        return (br, sink) -> {
            try {
                final String line = br.readLine();
                if (Objects.isNull(line)) {
                    sink.complete();
                } else {
                    sink.next(line);
                }
            } catch (IOException e) {
                sink.error(e);
            }
            return br;
        };
    }

    private Consumer<BufferedReader> closeReader() {
        return br -> {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

//    @Override
//    public Flux<String> getPublisher(String path) {
//        return Flux.generate(() -> {
//            try {
//                return new BufferedReader(new FileReader(path));
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        }, (reader, sink) -> {
//            try {
//                final String line = reader.readLine();
//                if (Objects.isNull(line)) {
//                    sink.complete();
//                } else {
//                    sink.next(line);
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//            return reader;
//        }, reader -> {
//            try {
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }

}
