package com.wordle.marcus.spring_wordle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringWordleController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
