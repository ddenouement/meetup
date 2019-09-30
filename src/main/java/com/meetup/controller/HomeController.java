package com.meetup.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class Home {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index() {
        return "src/main/resources/static/index.html";
    }
}
