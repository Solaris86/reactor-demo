package com.rp.sec04;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec08DefaultIfEmpty {

    public static void main(String[] args) {
        getOrderNumbers()
                .filter(integer -> integer > 10)
                .defaultIfEmpty(-100)
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 10);
    }

}
