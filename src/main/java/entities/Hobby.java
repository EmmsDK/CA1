package entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "hobby")
public class Hobby {
    @Id
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "description", length = 45)
    private String description;

    @ManyToMany
    @JoinTable(name = "person-hobby",
            joinColumns = @JoinColumn(name = "hobby_name"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> people = new LinkedHashSet<>();

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> persons) {
        this.people = persons;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}