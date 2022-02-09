package com.wordle.marcus.spring_wordle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class Wordle {
    List <String> words = new ArrayList<String>();
    private String solution = "marky";
    // private String guess = "";

    public Wordle() {
        BufferedReader reader;
        try { 

            reader = new BufferedReader(new FileReader("words.txt"));
            String line = reader.readLine();
            while(line != null) {
                words.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void chooseSolution() {
        Random rnd = new Random();
        int index = rnd.nextInt(words.size());
        this.solution = words.get(index);
    }

    public List<String> getWords() {
        return words;
    }

    public String getSolution(){
        chooseSolution();
        return this.solution;
    }

    
}
