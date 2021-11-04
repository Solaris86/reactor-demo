package com.rp.sec01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Classname : com.rp.sec01.FileServiceImplTest
 *
 * Created on: 04 Nov 2021
 *
 * Copyright (c) 2000- Global Payments, Ltd.
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
class FileServiceImplTest {

    private final FileService fileService = new FileServiceImpl();

    @Test
    void readFile() {
        StepVerifier.create(fileService.readFile("file01.txt"))
                .expectSubscription()
                .assertNext(Assertions::assertNull)
                .verifyComplete();
    }

    @Test
    void createFile() {
        StepVerifier.create(fileService.createFile("file01.txt", "This is dummy line #1\nThis is dummy line #2"))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    void deleteFile() {
        StepVerifier.create(fileService.deleteFile("file01.txt"))
                .expectSubscription()
                .verifyComplete();
    }
}