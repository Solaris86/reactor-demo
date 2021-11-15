package com.rp.sec04;

import com.rp.sec04.helper.Person;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Lec11SwitchOnFirst {

    public static void main(String[] args) {
        getPersons()
                .switchOnFirst((signal, personFlux) -> {
                    System.out.println("Inside switchOnFirst");
                    return signal.isOnNext() && signal.get().getAge() > 10 ? personFlux : applyFilterMap().apply(personFlux);
                })
                .subscribe(Util.subscriber());
    }

    private static Flux<Person> getPersons() {
        return Flux.range(1, 10)
                .map(i -> new Person());
    }

    private static Function<Flux<Person>, Flux<Person>> applyFilterMap() {
        return personFlux -> personFlux
                .filter(person -> person.getAge() > 10)
                .doOnNext(person -> person.setName(person.getName().toUpperCase()))
                .doOnDiscard(Person.class, person -> System.out.println("Not allowing : " + person));
    }

}
