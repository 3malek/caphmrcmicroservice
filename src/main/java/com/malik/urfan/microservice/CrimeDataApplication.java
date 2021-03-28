package com.malik.urfan.microservice;

import com.malik.urfan.microservice.domain.CrimeCategoryConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class CrimeDataApplication {

	private static final Logger log = LoggerFactory.getLogger(CrimeDataApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(CrimeDataApplication.class, args);
	}


	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) {
		return args -> {

			Calendar today = Calendar.getInstance();
			StringBuilder yearMonth = new StringBuilder( Integer.toString( today.get( Calendar.YEAR ) ) );
			yearMonth.append("-");
			String month = Integer.toString( today.get( Calendar.MONTH ) + 1 );

			if ( month.length() < 2 )
			{
				month = "0" + month;
			}
			yearMonth.append( month );

			log.info( "Date via Calendar: " + yearMonth );

			CrimeCategoryConsumer[] crimeCategories = restTemplate.getForObject(
					"https://data.police.uk/api/crime-categories?date="+yearMonth, CrimeCategoryConsumer[].class);

			log.info( "no. of crime categories: " + crimeCategories.length );
			Service service = new Service();
			service.setCrimeCategories( crimeCategories );
		};
	}
}