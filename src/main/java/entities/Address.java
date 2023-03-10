package entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "street", nullable = false, length = 90)
    private String id;

    @Column(name = "additionalInfo", length = 45)
    private String additionalInfo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cityInfo_zipCode", nullable = false)
    private CityInfo cityinfoZipcode;

    @OneToMany(mappedBy = "addressStreet")
    private Set<Person> people = new LinkedHashSet<>();

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public CityInfo getCityinfoZipcode() {
        return cityinfoZipcode;
    }

    public void setCityinfoZipcode(CityInfo cityinfoZipcode) {
        this.cityinfoZipcode = cityinfoZipcode;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}