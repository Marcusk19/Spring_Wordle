package com.wordle.marcus.spring_wordle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class SpringWordleController {
    Wordle wordle = new Wordle();

    @GetMapping("/words")
    String words(Model model){
        
        List <String> words = wordle.getWords();
        String result = "";
        Collections.sort(words);
        for(String word : words) {
            result += word.toUpperCase() + "\n";
        }
        model.addAttribute("result", result);
        return "words";
    }

    @GetMapping("/")
    public String homepage(Model model){
        String solution = wordle.getSolution().toUpperCase();
        model.addAttribute("solution", solution);
        return "homepage";
    }

    @GetMapping(value = "/index")
    public String index(Model model) {
        String solution = wordle.getSolution();
        model.addAttribute("solution", solution);
        return "index";
    }



}
