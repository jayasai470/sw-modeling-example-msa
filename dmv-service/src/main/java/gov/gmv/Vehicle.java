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
    @JoinColumn(name="owner_id")
    Owner owner;

    String modelName;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }


}
