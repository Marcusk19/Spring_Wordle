package com.wordle.marcus.spring_wordle;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class WordleController {
    Wordle wordle = new Wordle();

    @GetMapping("/solution")
    public String solution() {
        return wordle.getSolution();
    }

    @GetMapping("/valdiate")
    public String[] validate(@RequestParam(value="guess", defaultValue="     ")String guess) {
        return wordle.validateGuess(guess);
    }

    @GetMapping("/check_game")
    public String checkGame() {
        return wordle.checkGameOver();
    }

    @GetMapping("/choose_solution")
    public String choose_solution() {
        return wordle.chooseSolution();
    }
}
