package com.rp.sec04;

import com.rp.sec04.helper.OrderService;
import com.rp.sec04.helper.UserService;
import com.rp.util.Util;

public class Lec12FlatMap {

    public static void main(String[] args) {
        UserService.getUsers()
                .concatMap(user -> OrderService.getOrders(user.getUserId()))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

}
