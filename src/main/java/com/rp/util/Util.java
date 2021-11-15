package com.rp.util;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import org.reactivestreams.Subscriber;

import java.util.function.Consumer;

@UtilityClass
public class Util {

    private static final Faker FAKER = Faker.instance();

    public Consumer<Object> onNext() {
        return o -> System.out.println("Received: " + o);
    }

    public Consumer<Throwable> onError() {
        return e -> System.out.println("ERROR: " + e.getMessage());
    }

    public Runnable onComplete() {
        return () -> System.out.println("Completed");
    }

    public Faker faker() {
        return FAKER;
    }

    public void sleepSeconds(int seconds) {
        sleepMillis(seconds * 1000);
    }

    public void sleepMillis(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Subscriber<Object> subscriber() {
        return new DefaultSubscriber();
    }

    public Subscriber<Object> subscriber(String name) {
        return new DefaultSubscriber(name);
    }
}
