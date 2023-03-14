package entities;

import facades.CityInfoFacade;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "street", nullable = false, length = 90)
    private String street;

    @Column(name = "additionalInfo", length = 45)
    private String additionalInfo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cityInfo_zipCode", nullable = false)
    private CityInfo cityInfo;

    @OneToMany(mappedBy = "addressStreet")
    private Set<Person> people = new LinkedHashSet<>();

    public Address() {
    }

    public Address(String street, String additionalInfo, int zipCode) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityInfo = new CityInfo(zipCode);
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}