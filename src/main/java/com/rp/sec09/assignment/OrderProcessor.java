package com.rp.sec09.assignment;

import reactor.core.publisher.Flux;

import java.util.function.Function;

public interface OrderProcessor {

    Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> processOrder();

}
