
/*
 * Classname : com.rp.sec01.Lec09AssignmentDemo
 *
 * Created on: 04 Nov 2021
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
package com.rp.sec01;

import com.rp.assignment.FileServiceUtil;
import com.rp.util.Util;

public class Lec09AssignmentDemo {

    public static void main(String[] args) {
//        FileServiceUtil.read("file01.txt")
//                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

//        FileServiceUtil.write("file03.txt", "This is file3")
//                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        FileServiceUtil.delete("file03.txt")
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }

}
