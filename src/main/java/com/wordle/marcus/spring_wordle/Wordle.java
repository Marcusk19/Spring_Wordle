package com.wordle.marcus.spring_wordle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


public class Wordle {
    List <String> words = new ArrayList<String>();
    private String solution = "marky";
    // private String guess = "";
    URL url;
    String username = "user";
    String password = "passwd";
    public Wordle() {
 
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
