# todoApp
Todo Application with Angularjs and spring boots with mongoDB embedded
Spring boot application for the todo app


To Run this application go to TodoApp.java and just Run as Java Application
OR
Running as a Packaged Application
            java -jar todoapp-1.0.0-SNAPSHOT.jar
OR
Using the Maven Plugin
            mvn spring-boot:run

Now,
To Run the application from front end
localhost:8080/view
            
            
I have used here the embedde mongodb
below are the dependency:
<dependency>
			<groupId>de.flapdoodle.embed</groupId>
			<artifactId>de.flapdoodle.embed.mongo</artifactId>
			<version>1.50.5</version>
</dependency>
<dependency>
			<groupId>cz.jirutka.spring</groupId>
			<artifactId>embedmongo-spring</artifactId>
			<version>RELEASE</version>
</dependency>
