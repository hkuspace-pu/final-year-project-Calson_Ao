package uk.ac.plymouth.calson.fyp.emrs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EMRSApplication
{
	private static final Logger logger = LoggerFactory.getLogger(EMRSApplication.class.getName());
	
	public static void main(String[] args)
	{
		SpringApplication.run(EMRSApplication.class, args);
	}
}
