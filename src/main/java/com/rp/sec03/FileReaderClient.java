package com.rp.sec03;

import com.rp.util.Util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReaderClient {

    public static void main(String[] args) throws InterruptedException {
        final FileReaderService fileReaderService = new FileReaderServiceImpl();
        final Path path = Paths.get("src/main/resources/assignment/sec03/demo.txt");
        fileReaderService.getPublisher(path)
                        .subscribe(Util.subscriber());

//        Thread.sleep(10);
    }

}
