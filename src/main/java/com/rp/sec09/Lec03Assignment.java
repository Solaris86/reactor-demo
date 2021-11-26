package com.rp.sec09;

import com.rp.sec09.helper.BookOrder;
import com.rp.sec09.helper.RevenueReport;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Lec03Assignment {

    private static final Set<String> targetGenres = Set.of("Science fiction", "Fantasy", "Suspense/Thriller");

    public static void main(String[] args) {
        bookOrderStream()
                .filter(bookOrder -> targetGenres.contains(bookOrder.getCategory()))
                .buffer(Duration.ofSeconds(5))
//                .map(bookOrders -> bookOrders.stream().mapToDouble(BookOrder::getPrice).sum())
                .map(Lec03Assignment::revenueCalculator)
                .subscribe(revenueReport -> System.out.println("Revenue report : " + revenueReport));

//                .doOnNext(bookOrders -> {
//                    double sum = 0;
//                    for (BookOrder bookOrder : bookOrders) {
//                        if (targetGenres.contains(bookOrder.getBook().genre())) {
//                            sum += bookOrder.getPrice();
//                        }
//                    }
//
//                    System.out.println("Sum : " + sum);
//                })
//        .subscribe();

        Util.sleepSeconds(60);
    }

    private static RevenueReport revenueCalculator(List<BookOrder> bookOrders) {
        Map<String, Double> revenue = bookOrders.stream()
                .collect(
                        Collectors.groupingBy(BookOrder::getCategory,
                                Collectors.summingDouble(BookOrder::getPrice)));

        return new RevenueReport(revenue);
    }

    private static Flux<BookOrder> bookOrderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(l -> new BookOrder());
    }
}
