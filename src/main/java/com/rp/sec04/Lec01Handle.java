package com.rp.sec04;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec01Handle {

    public static void main(String[] args) {
        Flux.range(0, 10)
                .handle((integer, synchronousSink) -> {
                    if (integer == 7) {
                        synchronousSink.complete();
                    } else {
                        synchronousSink.next(integer );
                    }
                })
                .subscribe(Util.subscriber());
    }

}
