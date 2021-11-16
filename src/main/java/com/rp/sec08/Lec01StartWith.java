package com.rp.sec08;

import com.rp.sec08.helper.NameGenerator;
import com.rp.util.Util;

public class Lec01StartWith {

    public static void main(String[] args) {
        NameGenerator nameGenerator = new NameGenerator();
        nameGenerator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("mike"));

        nameGenerator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("sam"));

        nameGenerator.generateNames()
                .take(4)
                .subscribe(Util.subscriber("jake"));
    }

}
