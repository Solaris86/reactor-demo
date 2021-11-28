
/*
 * Classname : com.rp.sec09.assignment.OrderServiceImpl
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

import java.time.Duration;

public class OrderServiceImpl implements OrderService {

    @Override
    public Flux<PurchaseOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(100))
                .map(l -> new PurchaseOrder());
    }

}
