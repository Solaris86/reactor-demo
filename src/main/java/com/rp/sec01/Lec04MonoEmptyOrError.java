package com.rp.sec01;

import com.rp.util.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyOrError {

    public static void main(String[] args) {
        Mono<String> mono = userRepository(3);
        mono.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }

    private static Mono<String> userRepository(int id) {
        if (id == 1) {
            return Mono.just(Util.faker().name().fullName());
        } else if (id == 2) {
            return Mono.empty();
        } else {
            return Mono.error(new RuntimeException("Not in the allowed range"));
        }
    }

}
