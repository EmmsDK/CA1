package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person", indexes = {
        @Index(name = "email_UNIQUE", columnList = "email", unique = true),
        @Index(name = "fk_person_address1_idx", columnList = "address_street")
})
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "firstName", nullable = false, length = 45)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 45)
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false, referencedColumnName = "street")
    private Address address;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Phone> phones;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "person_hobby",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "hobby_name"))
    private List<Hobby> hobbies;

    public Person() {
    }

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hobbies = new ArrayList<>();
        this.phones = new ArrayList<>();
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }



    public void addPhone(Phone phone) {
        this.phones.add(phone);
        phone.setPerson(this); //Child gets a parent when parent gets the child
    }

    public void addHobby(Hobby hobby) {
        for (Hobby h : hobbies) {
            if (h.getName().equals(hobby.getName())) {
                return;
            }
        }
        this.hobbies.add(hobby);
        hobby.setPeople(hobby.getPeople()); //Child gets a parent when parent gets the child
    }

    @Override
    public String toString() {
        return "Person (ID): "+ id +
                "\nFirst name: " + firstName +
                "\nLast name: " + lastName +
                "\nEmail: " + email +
                "\nAddress: " + address;
    }
}

