package com.autoinsurance;

import java.io.Serializable;
import java.util.Date;

public class PremiumPaymentPK implements Serializable {
    InsurancePolicy insurancePolicy;
    private Date paymentDate;
}
