package com.rp.sec03;

import com.rp.sec03.helper.NameProducer;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.CountDownLatch;

public class Lec08FluxPush {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);

        final NameProducer nameProducer = new NameProducer();
        Flux.push(nameProducer)
                .subscribe(Util.subscriber());

        Runnable runnable = () -> nameProducer.produce(latch);
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }

        latch.await();
    }

}
