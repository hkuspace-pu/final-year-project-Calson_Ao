package uk.ac.plymouth.calson.fyp.emrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController
{
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class.getName());
	
	@GetMapping("/")
	public String index()
	{
		logger.info("Go to index page!");
		return "index";
	}
}
