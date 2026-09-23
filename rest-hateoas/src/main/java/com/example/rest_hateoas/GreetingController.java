package com.example.rest_hateoas;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
public class GreetingController {

    public static final String DEFAULT_VALUE = "World";
    public static final String TEMPLATE = "Hello, %s!";

    @RequestMapping("/greeting")
    public HttpEntity<Greeting> getGreeting(
            @RequestParam(value = "name", defaultValue = DEFAULT_VALUE) String name
    ){

        Greeting greeting = new Greeting(String.format(TEMPLATE, name));
        greeting.add(linkTo(methodOn(GreetingController.class).getGreeting(name)).withSelfRel());
        return new HttpEntity<>(greeting);
    }
}
