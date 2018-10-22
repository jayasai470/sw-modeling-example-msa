package gov.gmv;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created by uengine on 2017. 12. 3..
 */
@Entity
public class Owner {

    @Id
    String ssn;
    String name;

    @OneToOne(mappedBy = "owner")
    License license;

    @OneToMany(mappedBy = "owner")
    List<Vehicle> vehicles;

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }


    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }


}
