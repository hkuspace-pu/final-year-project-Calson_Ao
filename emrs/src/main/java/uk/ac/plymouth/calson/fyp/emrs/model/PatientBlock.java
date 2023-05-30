package uk.ac.plymouth.calson.fyp.emrs.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PatientBlock
{
	public static final String FIXED_HASH_PREFIX = "00";	// hash value of this block must starts with this prefix
	public static final int PREFIX_LENGTH = FIXED_HASH_PREFIX.length();
	
	// patient data
	private String name;
	private String sex;
	private String hkid;
	private String consultationData;
	
	// block additional data
	private long timestamp;			// timestamp of creating this block
	private int nonce;				// random number used in blockchain encryption
	private String prevHashValue;	// hash value of previous block
	private String hashValue;		// hash value of this block
	
	public PatientBlock()
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
	
	public String getPatientInfo()
	{
		String patientInfo = hkid + "_" + name + "_" + sex;
		return patientInfo;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getHkid()
	{
		return hkid;
	}

	public void setHkid(String hkid)
	{
		this.hkid = hkid;
	}

	public String getConsultationData()
	{
		return consultationData;
	}

	public void setConsultationData(String consultationData)
	{
		this.consultationData = consultationData;
	}

	public long getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(long timestamp)
	{
		this.timestamp = timestamp;
	}

	public int getNonce()
	{
		return nonce;
	}

	public void setNonce(int nonce)
	{
		this.nonce = nonce;
	}

	public String getPrevHashValue()
	{
		return prevHashValue;
	}

	public void setPrevHashValue(String prevHashValue)
	{
		this.prevHashValue = prevHashValue;
	}

	public String getHashValue()
	{
		return hashValue;
	}

	public void setHashValue(String hashValue)
	{
		this.hashValue = hashValue;
	}
}