package dtos;

import entities.Person;
import entities.Phone;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class PersonDTO implements Serializable {
    private  Integer id;
    private  String email;
    private  String firstName;
    private  String lastName;
    private List<PhoneDTO> phones;
    private  List<HobbyDTO> hobbies;

    public PersonDTO(Person person) {
        if(person.getId() != null)
            this.id = person.getId();

        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        person.getPhones().forEach(phone->this.phones.add(new PhoneDTO(phone)));
        person.getHobbies().forEach(hobby->this.hobbies.add(new HobbyDTO(hobby)));

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

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public List<HobbyDTO> getHobbies() {
        return hobbies;
    }

    public Person getEntity(){
        Person p = new Person(this.email, this.firstName, this.lastName);
        if(id != 0)
            p.setId(this.id);
        this.phones.forEach(phone->p.addPhone(phone.getEntity()));
        this.hobbies.forEach(hobby->p.addHobby(hobby.getEntity()));
        return p;
    }
    public void setId(int id) {
        this.id = id;
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
