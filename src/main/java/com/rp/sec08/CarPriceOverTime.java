package com.rp.sec08;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CarPriceOverTime {

    public static void main(String[] args) {
//        AtomicReference<Double> price = new AtomicReference<>();
//        price.set(10000D);
//        final Flux<AtomicReference<Double>> priceFlux = Flux.create(synchronousSink -> {
//                    while (price.get() >= 0) {
//                        price.set(price.get() - 100);
//
//                        if (price.get() <= 0) {
//                            synchronousSink.complete();
//                        }
//                        synchronousSink.next(price);
//                    }
//
//                });
//
//
//        final Flux<Double> demandFlux = Flux.generate(synchronousSink -> {
//                    double demand = ThreadLocalRandom.current().nextDouble(0.8, 1.2);
//                    Util.sleepMillis(100);
//                    synchronousSink.next(demand);
//                }).
//                cast(Double.class)
//                .startWith(1D);
//
//        Flux.combineLatest(priceFlux, demandFlux, (p, demand) -> p.get() * demand)
//                        .subscribe(Util.subscriber());
//
//        Util.sleepSeconds(60);

        final int startingPrice = 10000;
        Flux.combineLatest(monthStream(), demandStream(), (month, demand) -> (startingPrice - (month * 100)) * demand
        )
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Flux<Long> monthStream() {
        return Flux.interval(Duration.ZERO, Duration.ofSeconds(1));
    }

    private static Flux<Double> demandStream() {
        return Flux.interval(Duration.ofSeconds(3))
                .map(l -> Util.faker().random().nextInt(80, 120) / 100D)
                .startWith(1D);
    }

}
