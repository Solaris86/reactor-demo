package com.rp.sec05.assignment;

import com.rp.util.Util;

public class Lec06Assignment {

    public static void main(String[] args) {
        final OrderService orderService = new OrderService();
        final RevenueService revenueService = new RevenueService();
        final InventoryService inventoryService = new InventoryService();

        // revenue and inventory - observe the order stream
        orderService.orderStream().subscribe(revenueService.subscribeOrderStream());
        orderService.orderStream().subscribe(inventoryService.subscribeInventoryStream());

        inventoryService.inventoryStream()
                .subscribe(Util.subscriber("inventory"));

        revenueService.revenueStream()
                .subscribe(Util.subscriber("revenue"));

        Util.sleepSeconds(60);
    }

}
