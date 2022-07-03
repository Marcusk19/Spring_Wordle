# Spring Wordle #

### Summary ###
This project is a barebones clone of the game Wordle built with Spring Boot. The main purpose of this project was for me to get experience with how Spring Boot works along with how a web application is built. Main branch grabs wordlist from a github repository. To see code for grabbing words from a mysql database check out the *mysql* branch.

### Installing/Running ###
Clone this repo:<br>
```
git clone https://github.com/Marcusk19/springwordle.git
cd springwordle
```

Make sure you edit *src/main/resources/example_config.properties* file to the correct values <br>
Then you can build and run the project using gradle:<br>
```
gradle build
java -jar build/libs/spring_wordle-1.1.jar
```
Navigate to localhost and you should see the application.<br>
<img src="images/readme/wordle_homepage.png" align="center" />

## Docker ##
Recently added a Dockerfile to this project so that it can be run in a container.
```
docker build -t wordle:latest .   
docker run -p 80:80 wordle
```