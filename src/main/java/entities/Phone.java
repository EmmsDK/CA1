package entities;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @Column(name = "number", nullable = false)
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "description", length = 45)
    private String description;

    public Phone(Integer number, String description) {
        this.number = number;

        this.description = description;
    }

    public Phone() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}