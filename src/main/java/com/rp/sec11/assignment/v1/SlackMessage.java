package com.rp.sec11.assignment.v1;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Getter
@ToString
public class SlackMessage {

    private final String senderId;
    private final String senderName;
    private final String content;

    public String getContent() {
        return senderName + " " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + "\n" + content;
    }

}
