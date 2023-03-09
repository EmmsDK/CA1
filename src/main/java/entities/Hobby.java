package entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "hobby")
public class Hobby {
    @Id
    @Column(name = "name", nullable = false, length = 45)
    private String id;

    @Column(name = "description", length = 45)
    private String description;

    @ManyToMany
    @JoinTable(name = "person-hobby", joinColumns = {
            @JoinColumn(name = "person", referencedColumnName = "id", nullable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name = "hobby", referencedColumnName = "name", nullable = false)
    }
    )
    public Set<Person> people = new LinkedHashSet<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}