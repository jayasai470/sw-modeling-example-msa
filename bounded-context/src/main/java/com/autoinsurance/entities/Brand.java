package com.autoinsurance.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by uengine on 2017. 12. 3..
 */
@Entity
public class Brand {

    @Id
    String brandId;
    String name;
    String description;


    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
