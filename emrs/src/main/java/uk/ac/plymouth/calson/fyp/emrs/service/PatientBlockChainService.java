package uk.ac.plymouth.calson.fyp.emrs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.plymouth.calson.fyp.emrs.model.PatientBlock;

@Service
public class PatientBlockChainService
{
	private static final Logger logger = LoggerFactory.getLogger(PatientBlockChainService.class.getName());
	
	@Autowired
	private CryptoService cryptoService;
	
	private TreeMap<String, List<PatientBlock>> patientMap;		// store data of all patients, use patient's HKID to find its EMR blockchain  
	
	@PostConstruct
	public void init()
	{
		logger.info("init PatientBlockChainService ...");
		patientMap = new TreeMap<String, List<PatientBlock>>();
	}
	
//	public int getChainSize(String hkid)
//	{
//		List<PatientBlock> blockchain = patientMap.get(hkid);
//		if (CollectionUtils.isEmpty(blockchain)) {
//			return 0;
//		}
//		else {
//			return blockchain.size();	
//		}
//	}
	
	public List<String> findAllPatient()
	{
		List<String> hkidList = new ArrayList<String>( patientMap.keySet() );
		return hkidList;
	}
	
	public void createPatientBlock(String name, String sex, String hkid)
	{
		PatientBlock block = new PatientBlock();
		block.setName(name);
		block.setSex(sex);
		block.setHkid(hkid);
		
		List<PatientBlock> blockchain = new ArrayList<PatientBlock>();		// create a blockchain for this patient immediately
		patientMap.put(hkid, blockchain);
		block.setPrevHashValue(null);	// prepare the first block, which should has no previous hash value
		
		String myHash = cryptoService.mineBlock(block);
		block.setHashValue(myHash);
		blockchain.add(block);
		logger.info("patient[HKID={}] is created, the first block is added to chain, block=[{}]", hkid, block.toString());
	}
	
	public void updatePatientBlock(String hkid, String name, String sex, String consultationData)
	{
		PatientBlock block = new PatientBlock();
		block.setName(name);
		block.setSex(sex);
		block.setHkid(hkid);
		block.setConsultationData(consultationData);
		
		// find the blockchain for the patient by his HKID
		List<PatientBlock> blockchain = patientMap.get(hkid);
		PatientBlock prevBlock = blockchain.get(blockchain.size() - 1);		// get the last block in the chain
		String prevHash = prevBlock.getHashValue();
		block.setPrevHashValue(prevHash);			// set the previous hash for the new block
		
		String myHash = cryptoService.mineBlock(block);
		block.setHashValue(myHash);
		blockchain.add(block);
		logger.info("new block is created and added to blockchain, block=[{}]",  block.toString());
	}
	
	public boolean verify(String hkid)
	{
		// get the blockchain of a patient and verify it
		List<PatientBlock> blockchain = patientMap.get(hkid);
		
		boolean success = true;
		int i = 0;
		while (success && i < blockchain.size())
		{
			PatientBlock currentBlock = blockchain.get(i);
			String myHash1 = currentBlock.getHashValue();
			String myHash2 = cryptoService.computeHash(currentBlock);	// nonce already adjusted during mine before, myHash2 should starts with Block.FIXED_HASH_PREFIX
			if (myHash1.equals(myHash2))
			{
				String prefix1 = myHash1.substring(0, PatientBlock.PREFIX_LENGTH);
				if (prefix1.equals(PatientBlock.FIXED_HASH_PREFIX))
				{
					if (i > 0)
					{
						String prevHash1 = currentBlock.getPrevHashValue();
						PatientBlock prevBlock = blockchain.get(i - 1);
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
					logger.warn("saved hash of block[{}] does NOT start with {}", i, PatientBlock.FIXED_HASH_PREFIX);
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
