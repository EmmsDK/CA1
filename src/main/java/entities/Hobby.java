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

    @Column(name = "wikiLink", nullable = false)
    private String wikiLink;

    @Column(name = "category", nullable = false, length = 45)
    private String category;

    @Column(name = "type", nullable = false, length = 45)
    private String type;

    @ManyToMany
    @JoinTable(name = "person-hobby",
            joinColumns = @JoinColumn(name = "hobby_name", referencedColumnName = "name"),
            inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private Set<Person> people = new LinkedHashSet<>();

    public Hobby() {
    }

    public Hobby(String name, String wikiLink, String category, String type) {
        this.name = name;
        this.wikiLink = wikiLink;
        this.category = category;
        this.type = type;
        this.people=new ArrayList<>();
    }



    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> persons) {
        this.people = persons;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String id) {
        this.name = id;
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