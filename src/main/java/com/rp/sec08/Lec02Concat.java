package com.rp.sec08;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec02Concat {

    public static void main(String[] args) {
        Flux<String> flux1 = Flux.just("a", "b", "c")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> flux2 = Flux.error(new RuntimeException("Oops!"));
        Flux<String> flux3 = Flux.just("d", "e");

//        Flux<String> flux = flux1.concatWith(flux2);
//        Flux<String> flux = Flux.concat(flux1, flux2);
        Flux<String> flux = Flux.concatDelayError(flux1, flux2, flux3);

        flux.subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }

}
