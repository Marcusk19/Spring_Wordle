package com.wordle.marcus.spring_wordle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringWordleApplicationController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
