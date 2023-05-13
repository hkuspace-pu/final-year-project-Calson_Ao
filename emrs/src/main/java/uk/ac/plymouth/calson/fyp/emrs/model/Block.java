package uk.ac.plymouth.calson.fyp.emrs.model;

public class Block
{
	private String record;
	private long timeStamp;
	private int nonce;
	private String hashValue;
	private String prevHashValue;
	
	public String getRecord()
	{
		return record;
	}
	public void setRecord(String record)
	{
		this.record = record;
	}
	public long getTimeStamp()
	{
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp)
	{
		this.timeStamp = timeStamp;
	}
	public int getNonce()
	{
		return nonce;
	}
	public void setNonce(int nonce)
	{
		this.nonce = nonce;
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