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
@Table(name="Block")
public class Block {
	
	@Id
	@Column(name="BLOCK_CODE")
	private Long blockCode;
	
	@Column(name="BLOCK_NAME")
	private String blockName;

	public Long getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(Long blockCode) {
		this.blockCode = blockCode;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	 @ManyToMany(cascade = {CascadeType.ALL})
	 @JoinTable(name="BLOCK_PANCHAYAT",
	 			joinColumns={@JoinColumn(name="BLOCK_CODE")},
	 			inverseJoinColumns={@JoinColumn(name="PANCHAYAT_CODE")})
	 private Set<Panchayat> panchayats = new HashSet<Panchayat>();
	     
	 
	 public Set<Panchayat> getPanchayats() {
		return panchayats;
	}

	public void setPanchayats(Set<Panchayat> panchayats) {
		this.panchayats = panchayats;
	}

	@ManyToMany(mappedBy="blocks")
	 private Set<District> district = new HashSet<District>();
	     
	 
	
	public Block(Long blockCode, String blockName) {
		super();
		this.blockCode = blockCode;
		this.blockName = blockName;
	}
	
	public Block(){
	}
}
        