package com.wordle.marcus.spring_wordle;
import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;



public class Wordle {
    List <String> words = new ArrayList<String>();
    private String solution = "marky";
    // private String guess = "";
    String url = null;
    String username = null;
    String password = null;
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

    private void chooseSolution() {
        // select a random word from words to use as the solution
        if(words.size() <= 0) return; // don't do anything if we can't fetch from the db
        Random rnd = new Random();
        int index = rnd.nextInt(words.size());
        this.solution = words.get(index);
    }

    public List<String> getWords() {
        // handler function to return words array
        return words;
    }

    public String getSolution(){
        // handler funciton to return solution
        chooseSolution();
        return this.solution;
    }

    
}
