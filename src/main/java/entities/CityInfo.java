package entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "cityInfo")
public class CityInfo {
    @Id
    @Column(name = "zipCode", nullable = false)
    private Integer zipCode;

    @Column(name = "city", nullable = false, length = 90)
    private String city;

    @OneToMany(mappedBy = "cityinfoZipcode")
    private Set<Address> addresses = new LinkedHashSet<>();

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }
}