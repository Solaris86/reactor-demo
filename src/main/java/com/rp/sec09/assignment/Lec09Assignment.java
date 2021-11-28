package com.rp.sec09.assignment;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.function.Function;

public class Lec09Assignment {

    public static void main(String[] args) {
        Map<String, Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>>> map = Map.of(
                "Kids", new KidsPurchaseOrderProcessor().processOrder(),
                "Automotive", new AutomotivePurchaseOrderProcessor().processOrder()
        );

        new OrderServiceImpl().orderStream()
                .filter(p -> map.containsKey(p.getCategory()))
                .groupBy(PurchaseOrder::getCategory)
                .flatMap(gf -> map.get(gf.key()).apply(gf))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

}
