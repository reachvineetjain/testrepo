package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the oauthhistory database table.
 * 
 */
@Entity
@Table(name="oauthhistory")
@NamedQuery(name="OauthHistory.findAll", query="SELECT o FROM OauthHistory o")
public class OauthHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OauthHistoryPK id;

	public OauthHistory() {
	}

	public OauthHistoryPK getId() {
		return this.id;
	}

	public void setId(OauthHistoryPK id) {
		this.id = id;
	}

}