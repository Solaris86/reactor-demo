package com.rp.sec09.assignment;

import com.rp.util.Util;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class PurchaseOrder {

    private String id;
    private String item;
    private double price;
    private String category;
    private int quantity;

    public PurchaseOrder() {
        this.id = UUID.randomUUID().toString();
        this.item = Util.faker().commerce().productName();
        this.price = Double.parseDouble(Util.faker().commerce().price());
        this.category = Util.faker().commerce().department();
        this.quantity = Util.faker().random().nextInt(1, 10);
    }

}
