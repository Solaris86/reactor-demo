package com.rp.sec05;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec05HotPublishCache {

    public static void main(String[] args) {
        // share() = publish().refCount(1)
        // cache() = publish().replay()
        final Flux<String> movieStream = Flux.fromStream(Lec05HotPublishCache::getMovie)
                .delayElements(Duration.ofSeconds(1))
                .cache(2);

        Util.sleepSeconds(3);

        movieStream
                .subscribe(Util.subscriber("Sam"));

        Util.sleepSeconds(10);

        System.out.println("Mike is about to join");

        movieStream
                .subscribe(Util.subscriber("Mike"));

        Util.sleepSeconds(60);
    }

    // Movie theater
    private static Stream<String> getMovie() {
        System.out.println("Got the movie streaming request");
        return Stream.of("Scene1", "Scene2", "Scene3", "Scene4", "Scene5", "Scene6", "Scene7");
    }

}
