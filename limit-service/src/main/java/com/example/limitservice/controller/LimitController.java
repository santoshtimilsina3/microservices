package com.example.limitservice.controller;


import com.example.limitservice.bean.Limits;
import com.example.limitservice.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limit")
    public Limits getLimit() {
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
