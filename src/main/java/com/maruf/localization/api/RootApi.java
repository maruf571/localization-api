package com.maruf.localization.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/")
public class RootApi {

    @GetMapping
    public String welcomeMessage(){
        return "index";
    }
}
