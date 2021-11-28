package com.rp.sec09.helper;

import com.github.javafaker.Book;
import com.rp.util.Util;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class BookOrder {

    private final String id;
    private final String title;
    private final String author;
    private final String category;
    private final double price;

    public BookOrder() {
        final Book book = Util.faker().book();
        this.id = UUID.randomUUID().toString();
        this.title = book.title();
        this.author = book.author();
        this.category = book.genre();
        this.price = Double.parseDouble(Util.faker().commerce().price());
    }

}
