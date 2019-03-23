package com.maruf.localization.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootApi {

    @GetMapping
    public String welcomeMessage(){
        return "Welcome to Localization with 05";
    }
}
