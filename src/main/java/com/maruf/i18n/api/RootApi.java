package com.maruf.i18n.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class RootApi {

    @GetMapping
    public String welcomeMessage(){
        return "Welcome to Localization";
    }
}
