package uk.ac.plymouth.calson.fyp.emrs;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import uk.ac.plymouth.calson.fyp.emrs.model.Block;
import uk.ac.plymouth.calson.fyp.emrs.service.CryptoService;

@SpringBootApplication
public class MainApplication
{
	private static final Logger logger = LoggerFactory.getLogger(MainApplication.class.getName());
	
	@Autowired
	private CryptoService cryptoService;
	
	public static void main(String[] args)
	{
		SpringApplication.run(MainApplication.class, args);
		
		ApplicationContext ac = SpringApplication.run(MainApplication.class, args);
		CryptoService cryptoService = ac.getBean(CryptoService.class);
		
		ArrayList<Block> blockchain = new ArrayList<Block>();
		
		Block block1 = new Block();
		block1.setData("AAA");
		block1.setPrevHashValue(null);
		String hash = cryptoService.mineBlock(block1);
		block1.setHashValue(hash);
		blockchain.add(block1);
		logger.info(block1.toString());
		
		Block block2 = new Block();
		block2.setData("BBB");
		block2.setPrevHashValue(blockchain.get(blockchain.size() - 1).getHashValue());
		hash = cryptoService.mineBlock(block2);
		block2.setHashValue(hash);
		blockchain.add(block2);
		logger.info(block2.toString());
		
	}
}
