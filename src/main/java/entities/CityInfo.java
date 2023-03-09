package entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "cityInfo")
public class CityInfo {
    @Id
    @Column(name = "zipCode", nullable = false)
    private Integer id;

    @Column(name = "city", nullable = false, length = 90)
    private String city;

    public CityInfo() {
    }

    @OneToMany
    @JoinTable(name = "address-cityInfo", joinColumns = {
            @JoinColumn(name = "address", referencedColumnName = "street", nullable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name = "cityInfo", referencedColumnName = "zipCode", nullable = false)
    }
    )
    public Set<Address> addresses = new LinkedHashSet<>();

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}