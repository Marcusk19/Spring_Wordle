package com.wordle.marcus.spring_wordle;
import java.util.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.sql.*;
import java.net.URL;
import java.net.URLConnection;


public class Wordle {
    List <String> words = new ArrayList<String>();
    private String solution = "marky";
    private int numGuesses = 0;
    private boolean correctGuess = false;
    // private String guess = "";
    URL url = null;
    String username = null;
    String password = null;

    // constructor
    public Wordle() {
        // read in parameters from resources/config.properties
        try { 
            url = new URL("https://raw.githubusercontent.com/charlesreid1/five-letter-words/master/sgb-words.txt");
            URLConnection uc = url.openConnection();
            uc.setRequestProperty("X-Requested-With", "Curl");
            String userpass = username + ":" + password;
            String basicAuth = "Basic" + new String(Base64.getEncoder().encode(userpass.getBytes()));
            uc.setRequestProperty("Authorization", basicAuth);

            BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) 
                words.add(line);
            
            reader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        
    }

    public String chooseSolution() {
        // select a random word from words to use as the solution
        if(words.size() <= 0)  return "No list"; // don't do anything if we can't fetch from the db
        Random rnd = new Random();
        int index = rnd.nextInt(words.size());
        this.solution = words.get(index);
        numGuesses = 0;
        return "Solution chosen";
    }

    public String[] validateGuess(String guess) {
        guess = guess.toLowerCase();
        numGuesses++;
        String validation[] = new String[solution.length()];
        String tempSol = solution;
        int correct = 0;

        // check for valid input
        if(!(words.contains(guess)) || guess.length() < solution.length()) {
            validation[0] = "invalid";
            if(numGuesses > 0) numGuesses--;
            return validation;
        }

        // check for correctness
        for(int i = 0; i < guess.length(); i++) {
            char letter = guess.charAt(i);
            if(letter == tempSol.charAt(i)) {
                // add correct
                correct += 1;
                validation[i] = "correct";
                // remove correct char from tempSol so is not doubly checked
                tempSol = tempSol.substring(0, i) + "-" + tempSol.substring(i+1, tempSol.length());

            }
            else if(tempSol.contains(Character.toString(letter))) {
                // add present 
                validation[i] = "present";
            } else {
                // add absent
                validation[i] = "absent";
            }
        }
        if(correct == solution.length()) {
            correctGuess = true;
        }
        return validation;    
    }

    public List<String> getWords() {
        // handler function to return words array
        return words;
    }

    public String getSolution(){
        // handler funciton to return solution
        return this.solution.toUpperCase();
    }

    public String checkGameOver() {
        if(correctGuess || numGuesses >= 6) return "true";
        else return "false";
    }

    
}
