package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hobby")
public class Hobby {
    @Id
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "description", length = 45)
    private String description;

    private List<Person> people;

    public Hobby(String name, String description) {
        this.name = name;
        this.description = description;
        this.people=new ArrayList<>();
    }

    public Hobby() {
    }

    @ManyToMany
    @JoinTable(name = "person-hobby",
            joinColumns = @JoinColumn(name = "hobby_name"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))





    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> persons) {
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

    public void addPerson(Person person) {
        for (Person p : people) {
            if (p.getId()==person.getId()) {
                return;
            }
        }
        this.people.add(person);
        person.setHobbies(person.getHobbies()); //Child gets a parent when parent gets the child
    }
}