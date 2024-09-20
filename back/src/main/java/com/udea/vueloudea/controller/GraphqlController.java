package com.udea.vueloudea.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/flights")
public class GraphqlController {

    @QueryMapping
    public String helloWorld(@Argument String name) {
        return "Hello " + name;
    }

    @QueryMapping
    public String byeWorld(@Argument String name) {
        return "Bye " + name;
    }

    @MutationMapping
    public Boolean deleteWorld() {
        return false;
    }
}
