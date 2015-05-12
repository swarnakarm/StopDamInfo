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
@Table(name="State")
public class State {
	@Id
	@Column(name="STATE_CODE")
	private Long stateCode;
	
	@Column(name="STATE_NAME")
	private String stateName;


	public Long getStateCode() {
		return stateCode;
	}

	public void setStateCode(Long stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public State() {
		super();
	}

	public State(Long stateCode, String stateName) {
		super();
		this.stateCode = stateCode;
		this.stateName = stateName;
	}
	
	@ManyToMany(cascade = {CascadeType.ALL})
	 @JoinTable(name="STATE_DISTRICT",
	 			joinColumns={@JoinColumn(name="STATE_CODE")},
	 			inverseJoinColumns={@JoinColumn(name="DISTRICT_CODE")})
	 private Set<District> districts = new HashSet<District>();


	public Set<District> getDistricts() {
		return districts;
	}

	public void setDistricts(Set<District> districts) {
		this.districts = districts;
	}     
	

}
