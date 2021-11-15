package com.rp.util;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DefaultSubscriber implements Subscriber<Object> {

    private String name;

    public DefaultSubscriber() {
        this.name = "";
    }

    public DefaultSubscriber(String name) {
        this.name = name + " - ";
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Integer.MAX_VALUE);
    }

    @Override
    public void onNext(Object o) {
        System.out.println("Subscriber : " + name + " | Thread : " + Thread.currentThread().getName() + " | Received : " + o);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(name + "ERROR : " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println(name + "Completed");
    }
}
