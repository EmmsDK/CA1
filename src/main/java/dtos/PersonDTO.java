package dtos;
/*
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class PersonDTO implements Serializable {
    private final Integer id;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final Set<PhoneDTO> phones;
    private final Set<HobbyDTO> hobbies;

    public PersonDTO(Integer id, String email, String firstName, String lastName, Set<PhoneDTO> phones, Set<HobbyDTO> hobbies) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phones = phones;
        this.hobbies = hobbies;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<PhoneDTO> getPhones() {
        return phones;
    }

    public Set<HobbyDTO> getHobbies() {
        return hobbies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO entity = (PersonDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.phones, entity.phones) &&
                Objects.equals(this.hobbies, entity.hobbies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, lastName, phones, hobbies);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "email = " + email + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "phones = " + phones + ", " +
                "hobbies = " + hobbies + ")";
    }
}
*/