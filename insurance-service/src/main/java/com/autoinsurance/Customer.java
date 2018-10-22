/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: uengine
 * License Type: Purchased
 */
package com.autoinsurance;

import com.autoinsurance.services.CreditService;
import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;
import org.metaworks.annotation.*;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.multitenancy.persistence.BeforeSave;
import org.metaworks.multitenancy.persistence.TenantProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AddMetadataLink
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Customer")

/** uncommend if want to apply multi-tenancy support **/

//@Multitenant
//@TenantDiscriminatorColumn(
//		name = "TENANTID",
//		contextProperty = "tenant-id"
//)
@Configurable
public class Customer{

	@Column(name="Id", nullable=false, length=19)	
	@Id	
	@GeneratedValue(generator="COM_AUTOINSURANCE_CUSTOMER_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_AUTOINSURANCE_CUSTOMER_ID_GENERATOR", strategy="native")	
	private Long id;

	private String ssn;
	
	@Column(name="FirstName", nullable=true, length=255)
	private String firstName;
	
	@Column(name="LastName", nullable=true, length=255)
	private String lastName;
	
	@Column(name="Address", nullable=true, length=255)
	private String address;

	@Column(name="Age", nullable=false, length=10)
	private int age;

	@Column(name="BirthDay", nullable=true)
	@Temporal(TemporalType.DATE)
	private java.util.Date birthDay;

	@OneToMany(mappedBy="customer", targetEntity=com.autoinsurance.Vehicle.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private java.util.Set vehicles = new java.util.HashSet();

	private void setId(long value) {
		setId(new Long(value));
	}

	public void setId(Long value) {
		this.id = value;
	}

	@org.metaworks.annotation.Id
	@Hidden
	public Long getId() {
		return id;
	}

	public Long getORMID() {
		return getId();
	}

	public void setFirstName(String value) {
		this.firstName = value;
	}

	@Order(1)
	@Group(name="Default Information")
	@Face(displayName = "이름")
	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String value) {
		this.lastName = value;
	}

	@Order(2)
	@Group(name="Default Information")
	@Face(displayName = "성")
	public String getLastName() {
		return lastName;
	}

	public void setAddress(String value) {
		this.address = value;
	}

	@Order(3)
	@Face(displayName = "주소")
	@Group(name="Default Information")
	public String getAddress() {
		return address;
	}

	public void setAge(int value) {
		this.age = value;
	}

	@Order(4)
	@Face(displayName = "나이")
	@Group(name="Bio Information")
	public int getAge() {
		return age;
	}

	public void setBirthDay(java.util.Date value) {
		this.birthDay = value;
	}

	@Order(5)
	@Face(displayName = "생년월일")
	@Group(name="Bio Information")
	public java.util.Date getBirthDay() {
		return birthDay;
	}

	@Hidden
	public void setVehicles(java.util.Set value) {
		this.vehicles = value;
	}
	public java.util.Set getVehicles() {
		return vehicles;
	}


	public String toString() {
		return String.valueOf(getId());
	}

	@Transient
	@RestAssociation(
		path = "/pools/default/buckets/default/docs/{#tenant.tenantId}_{class.name}_{id}"
	)
	TenantProperties tenantProperties;
	@Hidden
		public TenantProperties getTenantProperties() {
			return tenantProperties;
		}
		public void setTenantProperties(TenantProperties tenantProperties) {
			this.tenantProperties = tenantProperties;
		}


	@Face(displayName = "주민등록번호")
	@NonEditable
	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}


	/** uncommend if want to apply credit validation before saving the customer info **/

	@PrePersist
	public void beforeSave() {

		CreditService creditService = MetaworksRemoteService.getInstance().getComponent(CreditService.class);

		try {
			if(creditService==null) throw new Exception("Credit Service is not available");

			if (creditService
					.getCredit(getSsn())
					.getCreditRate()
					.compareTo("B") > 0) {
				throw new RuntimeException("Your Credit is too low. SSN: " + getSsn());
			}
		}catch(Exception e){
			throw new RuntimeException("Check your SSN: " + getSsn(), e);
		}

	}

	private String driverLicenseNumber;



	public void setDriverLicenseNumber(String driverLicenseNumber) {
		this.driverLicenseNumber = driverLicenseNumber;
	}

	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}
}
