package com.rp.sec02;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class Lec04FluxFromStream {

    public static void main(String[] args) {
        final List<Integer> integers = List.of(1, 2, 3, 4, 5);
        final Stream<Integer> stream = integers.stream();

//        stream.forEach(System.out::println);
//        stream.forEach(System.out::println);

        final Flux<Integer> integerFlux = Flux.fromStream(integers::stream);

        integerFlux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        integerFlux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }

}
