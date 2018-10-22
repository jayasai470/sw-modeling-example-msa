package com.autoinsurance;


import com.autoinsurance.entities.License;
import com.autoinsurance.services.DMVService;
import org.metaworks.dwr.MetaworksRemoteService;

import javax.persistence.*;
import java.util.List;

@Entity
public class Policyholder {

	@Id @GeneratedValue
	@Column(name = "policyholder_id")
	private long id;

	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Customer customer;
	private String driverLicenseStatus;
	private String highestEduLevel;
	private String accidentHistory;
	private String emailAddress;
	private String healthInsurance;
	private String previousInsuranceCarrier;
	private String previousInsurancePolicyID;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getDriverLicenseStatus() {
		return driverLicenseStatus;
	}

	public void setDriverLicenseStatus(String driverLicenseStatus) {
		this.driverLicenseStatus = driverLicenseStatus;
	}

	public String getHighestEduLevel() {
		return highestEduLevel;
	}

	public void setHighestEduLevel(String highestEduLevel) {
		this.highestEduLevel = highestEduLevel;
	}

	public String getAccidentHistory() {
		return accidentHistory;
	}

	public void setAccidentHistory(String accidentHistory) {
		this.accidentHistory = accidentHistory;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getHealthInsurance() {
		return healthInsurance;
	}

	public void setHealthInsurance(String healthInsurance) {
		this.healthInsurance = healthInsurance;
	}

	public String getPreviousInsuranceCarrier() {
		return previousInsuranceCarrier;
	}

	public void setPreviousInsuranceCarrier(String previousInsuranceCarrier) {
		this.previousInsuranceCarrier = previousInsuranceCarrier;
	}

	public String getPreviousInsurancePolicyID() {
		return previousInsurancePolicyID;
	}

	public void setPreviousInsurancePolicyID(String previousInsurancePolicyID) {
		this.previousInsurancePolicyID = previousInsurancePolicyID;
	}

	public List<InsurancePolicy> getInsurancePolicy() {
		return insurancePolicy;
	}

	public void setInsurancePolicy(List<InsurancePolicy> insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}

	@OneToMany(mappedBy = "policyholder")
	private List<InsurancePolicy> insurancePolicy;

	public void registerVehicleToInsure(){

	}

	@PrePersist
	public void updatePolicyholder() {

		if(customer.getSsn()!=null){
			checkLicenseInformation();
		} else {
			throw new IllegalStateException("no social security number");
		}

	}

	private void checkLicenseInformation() {
		//Spring Version
		DMVService dmvService = MetaworksRemoteService.getComponent(DMVService.class);

		License license = dmvService.getLicense(customer.getSsn());

		customer.setDriverLicenseNumber(license.getDriverLicenseNumber());
		setDriverLicenseStatus(license.getDriverLicenseStatus());
		if(customer.getDriverLicenseNumber().isEmpty() || getDriverLicenseStatus().isEmpty()) {
			throw new IllegalStateException("Invalid DL");
		}
	}
}