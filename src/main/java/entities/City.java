package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "city")
public class City {
    @Id
    @Column(name = "zipCode", nullable = false)
    private Integer zipCode;

    @Column(name = "cityName", nullable = false, length = 90)
    private String cityName;

    @OneToMany(mappedBy = "city", cascade = CascadeType.PERSIST)
    private List<Address> addresses;

    public City(Integer zipCode, String cityName) {
        this.zipCode = zipCode;
        this.cityName = cityName;
        this.addresses = new ArrayList<>();
    }

    public City() {
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String city) {
        this.cityName = city;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }
    public void addAddress(Address address) {
        this.addresses.add(address);

        address.setCity(this); //Child gets a parent when parent gets the child

    }

    @Override
    public String toString() {
        return "City: " + cityName + ", " + zipCode;
    }
}
