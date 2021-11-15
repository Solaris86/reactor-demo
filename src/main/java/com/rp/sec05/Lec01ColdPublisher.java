package com.rp.sec05;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec01ColdPublisher {

    public static void main(String[] args) {
        final Flux<String> movieStream = Flux.fromStream(Lec01ColdPublisher::getMovie)
                .delayElements(Duration.ofSeconds(2));

        movieStream
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(5);

        movieStream
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(60);
    }

    // Netflix
    private static Stream<String> getMovie() {
        System.out.println("Got the movie streaming request");
        return Stream.of("Scene1", "Scene2", "Scene3", "Scene4", "Scene5", "Scene6", "Scene7");
    }

}
