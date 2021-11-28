package com.rp.sec11.assignment.v1;

import lombok.Getter;
import lombok.ToString;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Getter
@ToString
public class SlackRoom {

    private final String name;
    private final Sinks.Many<SlackMessage> sink;
    private final Flux<SlackMessage> flux;

    public SlackRoom(String name) {
        this.name = name;
        this.sink = Sinks.many().multicast().directAllOrNothing();
        this.flux = this.sink.asFlux();
    }

    public void joinRoom(SlackMember slackMember) {
        System.out.println(slackMember.getName() + " joined " + this.name);
        this.subscribe(slackMember);
    }

    private void subscribe(SlackMember slackMember) {
        this.flux
                .filter(slackMessage -> !slackMessage.getSenderId().equals(slackMember.getId()))
                .subscribe(new SlackSubscriber(slackMember));
    }

    public void publishMessage(String msg, SlackMember slackMember) {
        SlackMessage slackMessage = new SlackMessage(slackMember.getId(), slackMember.getName(), msg);
        this.sink.tryEmitNext(slackMessage);
    }
}
