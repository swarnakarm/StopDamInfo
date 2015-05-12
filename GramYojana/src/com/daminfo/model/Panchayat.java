package com.daminfo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Panchayat")
public class Panchayat {

	@Id
	@Column(name="PANCHAYAT_CODE")
	private Long panchayatCode;
	
	@Column(name="PANCHAYAT_NAME")
	private String panchayatName;



	public Long getPanchayatCode() {
		return panchayatCode;
	}

	public void setPanchayatCode(Long panchayatCode) {
		this.panchayatCode = panchayatCode;
	}

	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}

	public Panchayat() {
		super();
	}

	public Panchayat(Long panchayatCode, String panchayatName) {
		super();
		this.panchayatCode = panchayatCode;
		this.panchayatName = panchayatName;
	}
	
	@ManyToMany(cascade = {CascadeType.ALL})
	 @JoinTable(name="PANCHAYAT_VILLAGE",
	 			joinColumns={@JoinColumn(name="PANCHAYAT_CODE")},
	 			inverseJoinColumns={@JoinColumn(name="VILLAGE_CODE")})
	 private Set<Village> villages = new HashSet<Village>();
	     
	 public Set<Village> getVillages() {
		return villages;
	}

	public void setVillages(Set<Village> villages) {
		this.villages = villages;
	}

	@ManyToMany(mappedBy="panchayats")
	 private Set<Block> block = new HashSet<Block>();
	 
}
