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

    @OneToMany
    @JoinTable(name = "person-address", joinColumns = {
            @JoinColumn(name = "person", referencedColumnName = "id", nullable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name = "address", referencedColumnName = "street", nullable = false)
    }
    )
    public Set<Person> people = new LinkedHashSet<>();

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