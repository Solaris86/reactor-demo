package com.rp.sec01;

import com.rp.util.Util;
import reactor.core.publisher.Mono;

public class Lec05MonoFromSupplier {

    public static void main(String[] args) {
//        Mono<String> mono = Mono.just(getName());
        Mono.fromSupplier(Lec05MonoFromSupplier::getName)
                .subscribe(Util.onNext());

        Mono.fromCallable(Lec05MonoFromSupplier::getName)
                .subscribe(Util.onNext());
    }

    private static String getName() {
        System.out.println("Generating name...");
        return Util.faker().name().fullName();
    }

}
