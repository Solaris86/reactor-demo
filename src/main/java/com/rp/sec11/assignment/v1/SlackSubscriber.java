
/*
 * Classname : com.rp.sec11.assignment.v1.SlackSubscriber
 *
 * Created on: 26 Nov 2021
 *
 * Copyright (c) 2000-2021 Global Payments, Ltd.
 * Global Payments, The Observatory, 7-11 Sir John Rogerson's Quay, Dublin 2, Ireland
 *
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Global Payments, Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Global Payments.
 *
 */
package com.rp.sec11.assignment.v1;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@RequiredArgsConstructor
@Getter
public class SlackSubscriber implements Subscriber<SlackMessage> {

    private final SlackMember slackMember;

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Integer.MAX_VALUE);
    }

    @Override
    public void onNext(SlackMessage message) {
        System.out.println("-------------------------");
        System.out.println(slackMember.getName() + "'s Slack");
        System.out.println("-------------------------");
        System.out.println(message.getContent());
        System.out.print("\n");
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println("An error occurred: " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Completed");
    }
}
