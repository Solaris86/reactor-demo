package com.rp.sec05.assignment;


import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class InventoryService {

    private Map<String, Integer> db = new HashMap<>();

    public InventoryService() {
        db.put("Kids", 100);
        db.put("Automotive", 100);
    }

    public Consumer<PurchaseOrder> subscribeInventoryStream() {
        return p -> db.computeIfPresent(p.getCategory(), (k, v) -> v - p.getQuantity());
    }

    public Flux<String> inventoryStream() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> db.toString());
    }

//    public static void handlePublishOrderEvent() {
//        OrderService.publishPurchaseOrders()
//                .doOnNext(purchaseOrder -> System.out.println("Handling inventory for item: " + purchaseOrder.getItem()))
//                .subscribe(Util.subscriber(InventoryService.class.getSimpleName()));
//    }

}
