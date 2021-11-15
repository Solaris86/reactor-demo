package com.rp.sec02;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec09FluxFromMono {

    public static void main(String[] args) {
//        final Mono<String> mono = Mono.just("a");
//        final Flux<String> flux = Flux.from(mono);
//
//        flux.subscribe(Util.onNext());

        Flux.range(1, 10)
                .filter(i -> i > 3)
                .next()
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }

}
