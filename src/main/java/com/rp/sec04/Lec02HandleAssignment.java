package com.rp.sec04;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec02HandleAssignment {

    public static void main(String[] args) {
        Flux.generate(synchronousSink -> synchronousSink.next(Util.faker().country().name()))
                .map(Object::toString)
                .handle((country, synchronousSink) -> {
                    synchronousSink.next(country);
                    if ("canada".equalsIgnoreCase(country)) {
                        synchronousSink.complete();
                    }
                })
                .subscribe(Util.subscriber());
    }

}
