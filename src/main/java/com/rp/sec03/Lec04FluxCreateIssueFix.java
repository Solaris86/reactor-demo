package com.rp.sec03;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class Lec04FluxCreateIssueFix {

    public static void main(String[] args) {
        // only one instance of fluxSink
        AtomicInteger atomicInteger = new AtomicInteger(8);
        Flux.generate(synchronousSink -> {
            String country = Util.faker().country().name();
            System.out.println("Emitting : " + country);
            synchronousSink.next(country);
            atomicInteger.incrementAndGet();

            if ("canada".equalsIgnoreCase(country)) {
                synchronousSink.complete();
            }

        }).subscribe(Util.subscriber());
    }

}
