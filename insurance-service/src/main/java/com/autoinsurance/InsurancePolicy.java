package com.autoinsurance;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class InsurancePolicy {

	@Id @GeneratedValue
	private long id;
	private String state;
	private Date coveageStartDate;
	private BigDecimal insurancePremium;
	private String billPlan;
	private char[] signature;

	@OneToMany(mappedBy = "insurancePolicy")
	private List<PremiumPayment> premiumPayment;

	@ManyToOne
	@JoinColumn(name = "policyholder_id")
	private Policyholder policyholder;

	@OneToOne
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;

	@ManyToMany
	@JoinTable(
			name = "insurance_policy_coverage_item_option",
			inverseJoinColumns = @JoinColumn(name = "coverage_item_optionid"),
			joinColumns = @JoinColumn(name = "insurance_policy_id")
	)
	private Collection<CoverageItemOption> coverageItemOptions;

	@OneToMany(mappedBy = "insurancePolicy")
	List<InsuredDriver> insuredDrivers;



	/**
	 * Returns .InsurancePolicy.ID
	 * @param CustomerID
	 * @param VehicleID
	 */
	public String createPolicy(int CustomerID, String VehicleID) {
		// TODO - implement InsurancePolicy.createPolicy
		throw new UnsupportedOperationException();
	}

//	@PostUpdate
//	public void updatePolicy() {
//
//		switch (state){
//			case "Passed Eligibility Test":
//				if(this.policyholder.getEmailAddress().isEmpty()||
//						this.policyholder.getHealthInsurance().isEmpty()) {
//					throw new IllegalStateException("Please enter the email address and health insurance");
//				}
//				NIAService niaService = AutoInsuranceApplication.applicationContext.getBean(NIAService.class);
//				niaService.getInsuranceInformation(policyholder.getCustomer().getSocialSecurityNumber(),
//						vehicle.getID(),policyholder);
//
//
//		}
//
//	}

	public void producePolicyDocument() {
		// TODO - implement InsurancePolicy.producePolicyDocument
		throw new UnsupportedOperationException();
	}

}