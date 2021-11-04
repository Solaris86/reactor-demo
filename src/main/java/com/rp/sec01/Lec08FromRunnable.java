package com.rp.sec01;

import com.rp.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Lec08FromRunnable {

    public static void main(String[] args) {
//        Runnable runnable = () -> System.out.println("");

        Mono.fromRunnable(timeConsumingProcess())
//                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(Util.onNext(),
                        Util.onError(),
                        () -> System.out.println("Process is done. Sending emails..."));
    }

    private static Runnable timeConsumingProcess() {
        return () -> {
            Util.sleepSeconds(3);
            System.out.println("Operation completed");
        };
    }
}
