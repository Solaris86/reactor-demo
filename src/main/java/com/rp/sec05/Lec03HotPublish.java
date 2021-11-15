package com.rp.sec05;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec03HotPublish {

    public static void main(String[] args) {
        // share() = publish().refCount(1)
        final Flux<String> movieStream = Flux.fromStream(Lec03HotPublish::getMovie)
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .refCount(1);

        movieStream
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(10);

        movieStream
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(60);
    }

    // Movie theater
    private static Stream<String> getMovie() {
        System.out.println("Got the movie streaming request");
        return Stream.of("Scene1", "Scene2", "Scene3", "Scene4", "Scene5", "Scene6", "Scene7");
    }

}
