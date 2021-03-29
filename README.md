# README #

### What is this repository for? ###

* Spring Boot implementation that allows crime data requests via RESTful API
* Version 1.0


### How do I get set up? ###

You need to clone this repo someplace convenient on your workstation.

Project is set up so that the main program runs with maven (via command prompt under the top-level folder where you see a pom.xml file) using: 
mvnw spring-boot:run
(in Linux/Unix ./mvnw spring-boot:run).

(Port 8080 will be used so this needs to be available in order for the program to run as expected).

To check the server is working you can use the following URL (via an Internet browser, e.g IE, Firefox, etc):
http://localhost:8080/crime/categories

JSON output should be printed to the browser window/tab (like the following):
{"categories":["all-crime","anti-social-behaviour","bicycle-theft","burglary","criminal-damage-arson","drugs","other-theft","possession-of-weapons","public-order","robbery","shoplifting","theft-from-the-person","vehicle-crime","violent-crime","other-crime"]}


The other endpoint requires a postcode and date, e.g.:
http://localhost:8080/crimes?postcode=e14ns&date=2021-02 


You can also create a runnable JAR with the following command (which will be created under the target folder): 
mvnw package


### Who do I talk to? ###

Any issues, please use following contact details:
urfanmalik2000@yahoo.co.uk
07871 766 875