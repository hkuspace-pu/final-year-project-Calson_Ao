package uk.ac.plymouth.calson.fyp.emrs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import uk.ac.plymouth.calson.fyp.emrs.model.Block;
import uk.ac.plymouth.calson.fyp.emrs.service.BlockChainService;

@SpringBootApplication
public class MainApplication
{
	private static final Logger logger = LoggerFactory.getLogger(MainApplication.class.getName());
	
	public static void main(String[] args)
	{
		SpringApplication.run(MainApplication.class, args);
		
//		ApplicationContext ac = SpringApplication.run(MainApplication.class, args);
//		BlockChainService blockChainService = ac.getBean(BlockChainService.class);
//		blockChainService.create("Patient_1_Data");
//		blockChainService.create("Patient_2_Data");
//		blockChainService.create("Patient_3_Data");
//		blockChainService.verify();
//		Block block1 = blockChainService.get(0);
//		block1.setData("Patient_4_Data");
//		blockChainService.verify();
		
	}
}
