package uk.ac.plymouth.calson.fyp.emrs.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Block
{
//	public static final String HASH_PREFIX = StringUtils.leftPad("", 2, "0");
	public static final String FIXED_HASH_PREFIX = "00";	// hash value of this block must starts with this prefix
	public static final int PREFIX_LENGTH = FIXED_HASH_PREFIX.length();
	
	private long timestamp;			// timestamp of creating this block
	private String data;			// actual data (of transactions)
	private int nonce;				// random number used in blockchain encryption
	private String prevHashValue;	// hash value of previous block
	private String hashValue;		// hash value of this block
	
	public Block()
	{
		nonce = 1;
		timestamp = System.currentTimeMillis();
	}
	
	public void incrementNonce()
	{
		nonce++;
	}
	
	@Override
	public String toString()
	{
		return ReflectionToStringBuilder.toString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
	}
	
	public String getData()
	{
		return data;
	}
	public void setData(String data)
	{
		this.data = data;
	}
	
	public long getTimestamp()
	{
		return timestamp;
	}
	
	public int getNonce()
	{
		return nonce;
	}

	public String getHashValue()
	{
		return hashValue;
	}
	
	public void setHashValue(String hashValue)
	{
		this.hashValue = hashValue;
	}
	public String getPrevHashValue()
	{
		return prevHashValue;
	}
	public void setPrevHashValue(String prevHashValue)
	{
		this.prevHashValue = prevHashValue;
	}
}