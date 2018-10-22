package com.autoinsurance;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by uengine on 2018. 10. 21..
 */
@Entity
public class Credit {

    @Id
    String ssn;
    String creditRate;

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getCreditRate() {
        return creditRate;
    }

    public void setCreditRate(String creditRate) {
        this.creditRate = creditRate;
    }
}


