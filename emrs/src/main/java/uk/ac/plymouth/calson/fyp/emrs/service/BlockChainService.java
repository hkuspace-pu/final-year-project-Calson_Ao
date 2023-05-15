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
	
	public int getSize()
	{
		return blockchain.size();
	}
	
	public void create(String data)
	{
		Block block = new Block();
		block.setData(data);
		if (blockchain.isEmpty())
		{
			block.setPrevHashValue(null);	// the first block has no previous hash value
		}
		else
		{
			Block prevBlock = blockchain.get(blockchain.size() - 1);
			String prevHash = prevBlock.getHashValue();
			block.setPrevHashValue(prevHash);
		}
		
		String myHash = cryptoService.mineBlock(block);
		block.setHashValue(myHash);
		blockchain.add(block);
		logger.info("new block is created and added to blockchain, block=[{}]",  block.toString());
	}
	
	public Block get(int i)
	{
		return blockchain.get(i);
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
				String prefix1 = myHash1.substring(0, Block.PREFIX_LENGTH);
				if (prefix1.equals(Block.FIXED_HASH_PREFIX))
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
					logger.warn("saved hash of block[{}] does NOT start with {}", i, Block.FIXED_HASH_PREFIX);
					success = false;
				}
			}
			else
			{
				logger.warn("saved hash and computed hash of block[{}] NOT match! [{}] vs [{}]", i, myHash1, myHash2);
				success = false;
			}
			
			i++;
		}
		
		if (success)
		{
			logger.info("blockchain verified successfully!");
		}
		else
		{
			logger.warn("blockchain is INVALID!");
		}
		
		return success;
	}
}
