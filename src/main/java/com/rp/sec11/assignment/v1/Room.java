package com.rp.sec11.assignment.v1;

public interface Room {

    void subscribe(Member member);

    void publishMessage(SlackMessage message);

}
