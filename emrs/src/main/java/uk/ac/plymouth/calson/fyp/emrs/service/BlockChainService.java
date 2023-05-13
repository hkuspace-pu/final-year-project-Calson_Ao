package uk.ac.plymouth.calson.fyp.emrs.service;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.plymouth.calson.fyp.emrs.model.Block;

@Service
public class BlockChainService
{
	private static final Logger logger = LoggerFactory.getLogger(BlockChainService.class.getName());
	
	@Autowired
	private CryptoService cryptoService;
	
	private ArrayList<Block> blockchain;
	
	@PostConstruct
	public void init()
	{
		logger.info("init BlockChainService");
		blockchain = new ArrayList<Block>();
	}
	
	public boolean verify()
	{
		boolean success = true;
		int i = 0;
		while (success && i < blockchain.size())
		{
			Block currentBlock = blockchain.get(i);
			String myHash1 = currentBlock.getHashValue();
			String myHash2 = cryptoService.computeHash(currentBlock);	// nonce already adjusted during mine before, myHash2 should starts with Block.FIXED_HASH_PREFIX
			if (myHash1.equals(myHash2))
			{
				if (i > 0)
				{
					String prevHash1 = currentBlock.getPrevHashValue();
					Block prevBlock = blockchain.get(i - 1);
					String prevHash2 = prevBlock.getHashValue();
					
					if (!prevHash1.equals(prevHash2))
					{
						logger.warn("prev-hash of block[{}] and hash of block[{}] NOT match! [{}] vs [{}]", i, (i-1), prevHash1, prevHash2);
						success = false;
					}
				}
			}
			else
			{
				logger.warn("saved hash and computed hash of block[{}] NOT match! [{}] vs [{}]", i, myHash1, myHash2);
				success = false;
			}
			
			i++;
		}
		
		return success;
	}
}
