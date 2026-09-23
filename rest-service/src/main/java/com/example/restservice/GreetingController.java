package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String DEFAULT_VALUE = "World";
    private static final String template = "Hello, %s!";

    /*
     * Un AtomicLong es una clase de programación (como en Java o Kotlin) que permite manejar un número entero largo
     * (long de 64 bits) de forma segura cuando varios hilos de ejecución (threads) trabajan al mismo tiempo.
     */
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(defaultValue = DEFAULT_VALUE) String name) {
        return new Greeting(counter.incrementAndGet(), template.formatted(name));
    }
}
