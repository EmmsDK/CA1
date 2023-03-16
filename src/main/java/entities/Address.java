package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "street", nullable = false, length = 90)
    private String street;

    @Column(name = "additionalInfo", length = 45)
    private String additionalInfo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "city_zipCode", nullable = false, referencedColumnName = "zipCode")
    private City city;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<Person> people;

    public Address(){

    }

    public Address(String street, String additionalInfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.people = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City cityInfo) {
        this.city = cityInfo;
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
        return  street + " " + additionalInfo + ", " + city.getZipCode() + " " + city.getCityName();
    }
}