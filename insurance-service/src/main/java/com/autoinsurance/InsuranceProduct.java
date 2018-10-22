package com.autoinsurance;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InsuranceProduct {

	@Id
	private String ID;
	private String name;

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}