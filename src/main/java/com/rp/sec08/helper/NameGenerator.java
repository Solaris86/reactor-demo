package com.rp.sec08.helper;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {

    private final List<String> list = new ArrayList<>();

    public Flux<String> generateNames() {
        return Flux.generate(synchronousSink -> {
            System.out.println("Generated fresh");
            Util.sleepSeconds(1);
            String name = Util.faker().name().firstName();
            synchronousSink.next(name);
        })
                .cast(String.class)
                .doOnNext(list::add)
                .startWith(getFromCache());
    }

    private Flux<String> getFromCache() {
        return Flux.fromIterable(list);
    }

}
