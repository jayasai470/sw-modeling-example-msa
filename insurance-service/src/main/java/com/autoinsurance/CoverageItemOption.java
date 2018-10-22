package com.autoinsurance;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class CoverageItemOption {

	@ManyToOne(targetEntity = CoverageItem.class)
	@JoinColumn(name = "CoverageItemID")
	CoverageItem coverageItem;

	@Id
	private String ID;
	private BigDecimal value;

}