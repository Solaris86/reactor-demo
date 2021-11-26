package com.rp.sec11.assignment.v1;

import lombok.Data;

import java.util.UUID;

@Data
public class SlackMember {

    private final String id;
    private final String name;
    private final SlackRoom room;

    public SlackMember(String name, SlackRoom room) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.room = room;
    }

}
