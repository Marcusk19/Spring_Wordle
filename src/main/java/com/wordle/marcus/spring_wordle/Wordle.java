package com.wordle.marcus.spring_wordle;
import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;


public class Wordle {
    List <String> words = new ArrayList<String>();
    private String solution = "marky";
    private int numGuesses = 0;
    private boolean correctGuess = false;
    // private String guess = "";
    String url = null;
    String username = null;
    String password = null;

    // constructor
    public Wordle() {
        // read in parameters from resources/config.properties
        try {
            Properties prop = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");

            if(input != null) {
                prop.load(input);
            } else {
                System.out.println("Properties file not found");
            }

            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }


        
        // connection to mysql database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from five_letters");
            // add all results from result set to words array
            while(rs.next()) words.add(rs.getString(1)); 
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

    public String chooseSolution() {
        // select a random word from words to use as the solution
        if(words.size() <= 0)  return "No list"; // don't do anything if we can't fetch from the db
        Random rnd = new Random();
        int index = rnd.nextInt(words.size());
        this.solution = words.get(index);
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
            if(numGuesses < 0) numGuesses--;
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
        return this.solution;
    }

    public String checkGameOver() {
        if(correctGuess || numGuesses >= 6) return "true";
        else return "false";
    }

    
}
