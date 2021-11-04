package com.rp.util;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

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
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
