package entities;

import facades.CityInfoFacade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "street", nullable = false, length = 90)
    private String street;

    @Column(name = "additionalInfo", length = 45)
    private String additionalInfo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cityInfo_zipCode", nullable = false, referencedColumnName = "zipCode")
    private CityInfo cityInfo;

    @OneToMany(mappedBy = "addressStreet", cascade = CascadeType.ALL)
    private List<Person> people;

    public Address(){

    }

    public Address(String street, String additionalInfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.people = new ArrayList<>();
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
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
    public void addPerson(Person person) {
        this.people.add(person);
        person.setAddress(this); //Child gets a parent when parent gets the child
    }

    @Override
    public String toString() {
        return  street + " " + additionalInfo + ", " + cityInfo.getZipCode() + " " + cityInfo.getCity();
    }
}