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
@Table(name="District")
public class District {
	
		
		@Id
		@Column(name="DISTRICT_CODE")
		private Long districtCode;
		
		@Column(name="DISTRICT_NAME")
		private String districtName;

		public Long getDistrictCode() {
			return districtCode;
		}

		public void setDistrictCode(Long districtCode) {
			this.districtCode = districtCode;
		}

		public String getDistrictName() {
			return districtName;
		}

		public void setDistrictName(String districtName) {
			this.districtName = districtName;
		}

		 @ManyToMany(cascade = {CascadeType.ALL})
		 @JoinTable(name="DISTRICT_BLOCK",
		 			joinColumns={@JoinColumn(name="DISTRICT_CODE")},
		 			inverseJoinColumns={@JoinColumn(name="BLOCK_CODE")})
		 private Set<Block> blocks = new HashSet<Block>();
		     
		 public Set<Block> getBlocks() {
			return blocks;
		}

		public void setBlocks(Set<Block> blocks) {
			this.blocks = blocks;
		}

		@ManyToMany(mappedBy="districts")
		 private Set<State> state = new HashSet<State>();
		     
		 
		
		public District(Long districtCode, String districtName) {
			super();
			this.districtCode = districtCode;
			this.districtName = districtName;
		}
		
		public District(){
		}
	
}
