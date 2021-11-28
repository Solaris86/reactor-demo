package com.rp.sec09;

import com.rp.sec05.assignment.PurchaseOrder;
import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class Lec06Assignment {

    private static final AtomicBoolean atomicBoolean = new AtomicBoolean(true);

    public static void main(String[] args) {
        final Set<String> allowedCategories = Set.of("Kids", "Automotive");

        cratePurchaseOrderStream()
                .filter(purchaseOrder -> allowedCategories.contains(purchaseOrder.getCategory()))
//                .doOnNext(purchaseOrder -> System.out.println("Purchase order before change : " + purchaseOrder))
                .groupBy(PurchaseOrder::getCategory)
                .subscribe(Lec06Assignment::processPurchaseOrders);

        Util.sleepSeconds(60);
    }

    private static Flux<PurchaseOrder> cratePurchaseOrderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(l -> new PurchaseOrder());
    }

    private static void processPurchaseOrders(GroupedFlux<String, PurchaseOrder> purchaseOrderFlux) {
        purchaseOrderFlux
                .doOnNext(Lec06Assignment::updatePurchaseOrderBasedOnCategory)
                .subscribe(purchaseOrder -> System.out.println("Purchase order after change : " + purchaseOrder));
    }

    private static void updatePurchaseOrderBasedOnCategory(PurchaseOrder purchaseOrder) {
        if ("Kids".equals(purchaseOrder.getCategory())) {
            if (isValidKidsProductAction()) {
                purchaseOrder.setPrice(0.0);
                atomicBoolean.set(false);
            } else {
                purchaseOrder.setPrice(purchaseOrder.getPrice() * 0.5);
            }
        } else {
            purchaseOrder.setPrice(purchaseOrder.getPrice() * 1.1);
        }
    }

    private static boolean isValidKidsProductAction() {
        return atomicBoolean.get();
    }
}
