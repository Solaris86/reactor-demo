package com.rp.sec02;

import com.rp.util.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class Lec06Subscription {

    public static void main(String[] args) {
        AtomicReference<Subscription> atomicReference = new AtomicReference<>();
        Flux.range(1, 20)
                .log()
                .subscribeWith(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("Received Sub: " + subscription);
                        atomicReference.set(subscription);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("onError: " + throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

        final Subscription subscription = atomicReference.get();

        Util.sleepSeconds(3);
        subscription.request(3);
        Util.sleepSeconds(5);
        subscription.request(3);
        Util.sleepSeconds(5);
        System.out.println("going to cancel");
        subscription.cancel();
        Util.sleepSeconds(3);
        subscription.request(4);
        Util.sleepSeconds(3);
    }

}
