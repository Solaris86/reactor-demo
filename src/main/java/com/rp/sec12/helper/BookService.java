package com.rp.sec12.helper;

import com.rp.util.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BookService {

    private static final Map<String, Integer> MAP = new HashMap();

    static {
        MAP.put("std", 2);
        MAP.put("prime", 3);
    }

    public static Mono<String> getBook() {
        return Mono
                .deferContextual(ctx -> ctx.get("allow") ? Mono.just(Util.faker().book().title()) : Mono.error(new RuntimeException("not-allowed")))
                .contextWrite(rateLimiterContext());
    }

    private static Function<Context, Context> rateLimiterContext() {
        return ctx -> {
            if (ctx.hasKey("category")) {
                final String category = ctx.get("category").toString();
                final Integer attempts = MAP.get(category);
                if (attempts > 0) {
                    MAP.put(category, attempts - 1);
                    return ctx.put("allow", true);
                }
            }

            return ctx.put("allow", false);
        };
    }
}
