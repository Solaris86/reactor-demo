package com.rp.sec03;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {

    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            String country;

            do {
                country = Util.faker().country().name();
                fluxSink.next(country);
            } while (!country.equalsIgnoreCase("canada"));

            fluxSink.complete();
        }).subscribe(Util.subscriber());
    }

}
