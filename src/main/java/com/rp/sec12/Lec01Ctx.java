
/*
 * Classname : com.rp.sec12.Lec01Ctx
 *
 * Created on: 26 Nov 2021
 *
 * Copyright (c) 2000-2021 Global Payments, Ltd.
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
package com.rp.sec12;

import com.rp.util.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec01Ctx {

    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(context -> context.put("user", context.get("user").toString().toUpperCase()))
                .contextWrite(Context.of("user", "Sam"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> ctx.hasKey("user") ?
                Mono.just("Welcome " + ctx.get("user")) : Mono.error(new RuntimeException("unauthenticated")));
    }
}
