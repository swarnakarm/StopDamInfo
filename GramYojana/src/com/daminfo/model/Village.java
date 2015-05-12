package com.daminfo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="VILLAGE")
public class Village {
	@Id
	@Column(name="VILLAGE_CODE")
	private Long villageCode;
	
	@Column(name="VILLAGE_NAME")
	private String villageName;


	public Long getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(Long villageCode) {
		this.villageCode = villageCode;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public Village() {
		super();
	}

	public Village(Long villageCode, String villageName) {
		super();
		this.villageCode = villageCode;
		this.villageName = villageName;
	}
	
	     
	 @ManyToMany(mappedBy="villages")
	 private Set<Panchayat> panchayat = new HashSet<Panchayat>();
	 
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "village")
    private Set<GateInfo> gateInfos;


	public Set<Panchayat> getPanchayat() {
		return panchayat;
	}

	public void setPanchayat(Set<Panchayat> panchayat) {
		this.panchayat = panchayat;
	}

	public Set<GateInfo> getGateInfos() {
		return gateInfos;
	}

	public void setGateInfos(Set<GateInfo> gateInfos) {
		this.gateInfos = gateInfos;
	}

	
}
