package uk.ac.plymouth.calson.fyp.emrs.service;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import uk.ac.plymouth.calson.fyp.emrs.model.Block;

@Service
public class CryptoService
{
	private static final Logger logger = LoggerFactory.getLogger(CryptoService.class.getName());
	
	private DigestUtils hashUtil;
	
	@PostConstruct
	public void init()
	{
		logger.info("init CryptoService");
		hashUtil = new DigestUtils(MessageDigestAlgorithms.SHA_256);
	}
	
	public String computeHash(Block block)
	{
		String input = block.getTimestamp() + block.getData() + block.getNonce() + block.getPrevHashValue();
		String output = hashUtil.digestAsHex(input);
//		logger.info("[{}] + [{}] = {}", block.getData(), block.getNonce(), output);
		return output;
	}
	
	public String mineBlock(Block block)
	{
		logger.info("start to mine block [{}]...", block.getData());
		String hashValue = computeHash(block);
		String currentPrefix = hashValue.substring(0, Block.PREFIX_LENGTH);
		while (!currentPrefix.equals(Block.FIXED_HASH_PREFIX)) {
			block.incrementNonce();
			hashValue = computeHash(block);
			currentPrefix = hashValue.substring(0, Block.PREFIX_LENGTH);
	    }
		logger.info("final hash for block [{}]: {}", block.getData(), hashValue);
	    return hashValue;
	}
	
	 
}
