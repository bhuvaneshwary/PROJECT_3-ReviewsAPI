package com.udacity.course3.reviews;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication

public class ReviewsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ReviewsApplication.class, args);
		
		/* ******************** Flyway to create tables in the db *********** */
		
		//Flyway flyway = Flyway.configure().dataSource(url, username, password).load();
		Flyway flyway = Flyway.configure().dataSource("jdbc:mysql://localhost:3306/jdnd_p03", "root", "root").load();
		
		
		//If a schema already exists we use baseline to create a baseline if it doesn't exist
		//flyway.baseline();
		
		//To correct any errors in the flyway migrations, before starting a new migration
		//flyway.repair();
		
		//To create the tables from the resources/db/migrations/
		flyway.migrate();
		
		
	}

}