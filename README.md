# README #

### What is this repository for? ###

* Spring Boot implementation that allows crime data requests via RESTful API
* Version 1.0


### How do I get set up? ###

You need to clone this repo someplace convenient on your workstation.

Project is set up so that the main program runs with maven using (via command prompt): mvnw spring-boot:run
(in Linux/Unix ./mvnw spring-boot:run)

(Port 8080 will be used so this needs to be available in order for the program to run correctly)

To check the server is working you can use the following URL (via an Internet browser, e.g IE, Firefox, etc):
http://localhost:8080/crime/categories

A JSON string should be printed to the browser window/tab (like the following):
{"categories":["all-crime","anti-social-behaviour","bicycle-theft","burglary","criminal-damage-arson","drugs","other-theft","possession-of-weapons","public-order","robbery","shoplifting","theft-from-the-person","vehicle-crime","violent-crime","other-crime"]}

You can also create a runnable JAR with the following command:  mvnw package


### Who do I talk to? ###

Any issues, please use following contact details:
urfanmalik@outlook.com
07838 948 581