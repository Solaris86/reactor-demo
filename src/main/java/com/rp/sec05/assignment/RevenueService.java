package com.rp.sec05.assignment;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RevenueService {

    private Map<String, Double> db = new HashMap<>();

    public RevenueService() {
        db.put("Kids", 0.0);
        db.put("Automotive", 0.0);
    }

    public Consumer<PurchaseOrder> subscribeOrderStream() {
        return p -> db.computeIfPresent(p.getCategory(), (k, v) -> v + p.getPrice());
    }

    public Flux<String> revenueStream() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> db.toString())
                .subscribeOn(Schedulers.boundedElastic());
    }

    //    public static void handlePublishOrderEvent() {
//        OrderService.publishPurchaseOrders()
//                .doOnNext(purchaseOrder -> System.out.println("Handling revenue for item: " + purchaseOrder.getItem()))
//                .subscribe(Util.subscriber(RevenueService.class.getSimpleName()));
//    }

}
