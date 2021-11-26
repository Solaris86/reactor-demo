
/*
 * Classname : com.rp.sec09.assignment.PurchaseOrderProcessor
 *
 * Created on: 18 Nov 2021
 *
 * Copyright (c) 2000- Global Payments, Ltd.
 * Global Payments, The Observatory, 7-11 Sir John Rogerson's Quay, Dublin 2, Ireland
 *
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Global Payments, Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Global Payments.
 *
 */
package com.rp.sec09.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class KidsPurchaseOrderProcessor implements OrderProcessor {

    @Override
    public Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> processOrder() {
        return flux -> flux
                .doOnNext(purchaseOrder -> purchaseOrder.setPrice(0.5 * purchaseOrder.getPrice()))
                .flatMap(purchaseOrder -> Flux.concat(Mono.just(purchaseOrder), getFreeKidsOrder()));
    }

    private Mono<PurchaseOrder> getFreeKidsOrder() {
        return Mono.fromSupplier(() -> {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setItem("FREE - " + purchaseOrder.getItem());
            purchaseOrder.setCategory("Kids");
            purchaseOrder.setPrice(0);

            return purchaseOrder;
        });
    }
}
