package com.rp.sec02.helper;

import com.rp.util.Util;
import lombok.experimental.UtilityClass;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class NameGenerator {

//    public List<String> getNames(int count) {
//        return Stream.generate(NameGenerator::getName)
//                .limit(count)
//                .collect(Collectors.toList());
//    }

    public Flux<String> getNames(int count) {
        return Flux.range(0, count)
                .map(i -> getName());
    }

    private String getName() {
        Util.sleepSeconds(1);
        return Util.faker().name().fullName();
    }

}
