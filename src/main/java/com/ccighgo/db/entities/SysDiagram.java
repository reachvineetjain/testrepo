package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SysDiagrams database table.
 * 
 */
@Entity
@Table(name="SysDiagrams")
@NamedQuery(name="SysDiagram.findAll", query="SELECT s FROM SysDiagram s")
public class SysDiagram implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int diagramId;

	@Lob
	private byte[] definition;

	@Column(nullable=false, length=160)
	private String name;

	@Column(nullable=false)
	private int principalId;

	private int version;

	public SysDiagram() {
	}

	public int getDiagramId() {
		return this.diagramId;
	}

	public void setDiagramId(int diagramId) {
		this.diagramId = diagramId;
	}

	public byte[] getDefinition() {
		return this.definition;
	}

	public void setDefinition(byte[] definition) {
		this.definition = definition;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrincipalId() {
		return this.principalId;
	}

	public void setPrincipalId(int principalId) {
		this.principalId = principalId;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}