package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the oauth_history database table.
 * 
 */
//@Embeddable
public class CciOauthHistoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=40)
	private String nonce;

	@Column(unique=true, nullable=false, precision=10)
	private long timevalue;

    public CciOauthHistoryPK() {
    }
	public String getNonce() {
		return this.nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public long getTimevalue() {
		return this.timevalue;
	}
	public void setTimevalue(long timevalue) {
		this.timevalue = timevalue;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CciOauthHistoryPK)) {
			return false;
		}
		CciOauthHistoryPK castOther = (CciOauthHistoryPK)other;
		return 
			this.nonce.equals(castOther.nonce)
			&& (this.timevalue == castOther.timevalue);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.nonce.hashCode();
		hash = hash * prime + ((int) (this.timevalue ^ (this.timevalue >>> 32)));
		
		return hash;
    }
}