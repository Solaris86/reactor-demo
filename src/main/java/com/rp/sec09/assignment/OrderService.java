package com.rp.sec09.assignment;

import reactor.core.publisher.Flux;

public interface OrderService {

    Flux<PurchaseOrder> orderStream();

}
