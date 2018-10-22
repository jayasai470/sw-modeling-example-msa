package com.autoinsurance.entities;

import java.util.Date;

/**
 * Created by uengine on 2018. 10. 22..
 */
public class License {

    String driverLicenseNumber;
    Date issueDate;
    private String driverLicenseStatus;

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getDriverLicenseStatus() {
        return driverLicenseStatus;
    }

    public void setDriverLicenseStatus(String driverLicenseStatus) {
        this.driverLicenseStatus = driverLicenseStatus;
    }
}
