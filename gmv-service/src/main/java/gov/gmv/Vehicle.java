package gov.gmv;

import javax.persistence.*;

/**
 * Created by uengine on 2017. 12. 3..
 */
@Entity
public class Vehicle {

    @Id
    String vehicleId;

    @ManyToOne
    @JoinColumn(name="brandId")
    Brand brand;

    String name;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }


}
