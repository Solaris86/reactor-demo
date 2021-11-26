package com.rp.sec11.assignment.v2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Getter
@Setter
public class SlackMember {

    private final String name;
    private Consumer<String> messageConsumer;

    public void receives(String message) {
        System.out.println(message);
    }

    public void says(String message) {
        this.messageConsumer.accept(message);
    }

}
