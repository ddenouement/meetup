package com.meetup.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HelloController {

    @RequestMapping(value="/hello", method=GET)
    @ResponseBody
    public String foo() {
        return "{\"a\": \"b\"}";
    }

}
