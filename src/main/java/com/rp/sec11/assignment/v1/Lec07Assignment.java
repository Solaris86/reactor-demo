package com.rp.sec11.assignment.v1;

import com.rp.util.Util;

public class Lec07Assignment {

    public static void main(String[] args) {
        final SlackRoom slackRoom = new SlackRoom("reactor");

        SlackMember mike = new SlackMember("Mike", slackRoom);
        SlackMember jake = new SlackMember("Jake", slackRoom);

        slackRoom.joinRoom(mike);
        slackRoom.joinRoom(jake);

        slackRoom.publishMessage("Hello", mike);

        Util.sleepSeconds(1);

        slackRoom.publishMessage("How are you?", jake);

        Util.sleepSeconds(1);

        slackRoom.publishMessage("What's new?", jake);

        SlackMember jill = new SlackMember("Jill", slackRoom);
        slackRoom.joinRoom(jill);

        Util.sleepSeconds(2);

        slackRoom.publishMessage("Hey all!!!", jill);
    }

}
