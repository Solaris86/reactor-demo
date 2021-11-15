package com.rp.sec05.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Objects;

public class OrderService {

    private Flux<PurchaseOrder> flux;

    public Flux<PurchaseOrder> orderStream() {
        if (Objects.isNull(flux)) {
            flux = getOrderStream();
        }

        return flux;
    }

    private Flux<PurchaseOrder> getOrderStream() {
        return Flux.interval(Duration.ofMillis(100))
                .map(i -> new PurchaseOrder())
                .publish()
                .refCount(2);
    }

//    public static Flux<PurchaseOrder> publishPurchaseOrders() {
//        return Flux.range(1, 100)
//                .map(PurchaseOrder::new)
//                .delayElements(Duration.ofSeconds(1))
//                .share();
//    }

}
