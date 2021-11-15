package com.rp.sec02;

import com.rp.util.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class StockPricePublisher {

    private static final long STOCK_VALUE_UPDATE_EVENT_INTERVAL = 1L;
    private static final int STOCK_PRICE_STARTING_VALUE = 100;
    private static final int STOCK_PRICE_MIN_VALUE = 90;
    private static final int STOCK_PRICE_MAX_VALUE = 110;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        StockPricePublisher.getStockPricePublisher()
                .takeUntil(stockPrice -> stockPrice <= STOCK_PRICE_MIN_VALUE || stockPrice >= STOCK_PRICE_MAX_VALUE)
                .doOnNext(stockPrice -> System.out.println(LocalDateTime.now() + " Price: " + stockPrice))
                .doOnError(ex -> latch.countDown())
                .doOnComplete(latch::countDown)
                .subscribe();
//                        .subscribeWith(new Subscriber<Integer>() {
//
//                            private Subscription subscription;
//
//                            @Override
//                            public void onSubscribe(Subscription subscription) {
//                                this.subscription = subscription;
//                                subscription.request(Integer.MAX_VALUE);
//                            }
//
//                            @Override
//                            public void onNext(Integer stockPrice) {
//                                System.out.println(LocalDateTime.now() + " Price: " + stockPrice);
//                                if (stockPrice <= STOCK_PRICE_MIN_VALUE || stockPrice >= STOCK_PRICE_MAX_VALUE) {
//                                    subscription.cancel();
//                                    latch.countDown();
//                                }
//                            }
//
//                            @Override
//                            public void onError(Throwable throwable) {
//                                latch.countDown();
//                            }
//
//                            @Override
//                            public void onComplete() {
//                                latch.countDown();
//                            }
//                        });

        latch.await();
    }

    private static Flux<Integer> getStockPricePublisher() {
        AtomicInteger stockPriceValue = new AtomicInteger(STOCK_PRICE_STARTING_VALUE);
        return Flux.interval(Duration.ofSeconds(STOCK_VALUE_UPDATE_EVENT_INTERVAL))
                .map(pulse -> stockPriceValue.accumulateAndGet(
                        ThreadLocalRandom.current().nextInt(-5, +6),
                        Integer::sum));
    }
}
