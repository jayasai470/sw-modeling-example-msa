package com.autoinsurance;

/**
 * Created by uengine on 2018. 10. 24..
 */
public class CustomerRegisteredEvent {


    String couponNumber;
        public String getCouponNumber() {
            return couponNumber;
        }
        public void setCouponNumber(String couponNumber) {
            this.couponNumber = couponNumber;
        }

    String customerName;

    String emailAddress;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
