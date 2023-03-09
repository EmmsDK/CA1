package entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "person", indexes = {
        @Index(name = "email_UNIQUE", columnList = "email", unique = true)
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

    @OneToMany
    @JoinTable(name = "person-phone", joinColumns = {
            @JoinColumn(name = "person", referencedColumnName = "id", nullable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name = "phone", referencedColumnName = "number", nullable = false)
    }
    )
    public Set<Phone> phones = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "person-hobby", joinColumns = {
            @JoinColumn(name = "person", referencedColumnName = "id", nullable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name = "hobby", referencedColumnName = "name", nullable = false)
    }
    )
    public Set<Hobby> hobbies = new LinkedHashSet<>();



}