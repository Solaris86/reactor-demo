package com.rp.sec02;

import com.rp.sec02.helper.NameGenerator;
import com.rp.util.Util;

import java.util.List;

public class Lec07FluxVsList {

    public static void main(String[] args) {
//        final List<String> names = NameGenerator.getNames(5);
//        System.out.println(names);

        NameGenerator.getNames(5)
                .subscribe(Util.onNext());
    }

}
