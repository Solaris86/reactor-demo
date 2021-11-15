package com.rp.sec04;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec03DoCallbacks {

    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            for (int i = 0; i < 5; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
        })
        .doOnSubscribe(subscription -> System.out.println("doOnSubscribe : " + subscription))
        .doOnRequest(value -> System.out.println("doOnRequest : " + value))
        .doFirst(() -> System.out.println("doFirst"))
        .doOnNext(o -> System.out.println("doOnNext : " + o))
        .doOnError(err -> System.out.println("doOnError : " + err.getMessage()))
        .doOnDiscard(Object.class, o -> System.out.println("doOnDiscard : " + o))
        .doOnTerminate(() -> System.out.println("doOnTerminate"))
        .doOnCancel(() -> System.out.println("doOnCancel"))
        .doOnComplete(() -> System.out.println("doOnComplete"))
        .doFinally(signalType -> System.out.println("doFinally : " + signalType))
        .subscribe(Util.subscriber());
    }

}
