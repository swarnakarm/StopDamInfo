package com.daminfo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="GateInfo")
public class GateInfo {

	public GateInfo(){
		super();
	}
	
	@Column(name = "open_Date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date openDate;
	
	@Column(name = "close_Date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date closeDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VILLAGE_CODE", nullable = false)
	Village village;
	
	@Id
	@Column(name="ID")
	private Long id;
	
	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}
	
}
