package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the oauth_history database table.
 * 
 */
//@Entity
@Table(name="oauth_history")
public class CciOauthHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CciOauthHistoryPK id;

    public CciOauthHistory() {
    }

	public CciOauthHistoryPK getId() {
		return this.id;
	}

	public void setId(CciOauthHistoryPK id) {
		this.id = id;
	}
	
}