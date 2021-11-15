package com.rp.sec04.helper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class OrderService {

    private static Map<Integer, List<PurchaseOrder>> db = Map.of(
            1, List.of(new PurchaseOrder(1), new PurchaseOrder(1), new PurchaseOrder(1)),
            2, List.of(new PurchaseOrder(2), new PurchaseOrder(2)));

    public static Flux<PurchaseOrder> getOrders(int userId) {
        return Flux.create((FluxSink<PurchaseOrder> sink) -> {
            db.get(userId).forEach(sink::next);
            sink.complete();
        })
                .delayElements(Duration.ofSeconds(1));
    }

}
