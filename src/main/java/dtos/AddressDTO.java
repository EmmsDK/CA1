package dtos;

import entities.Address;
import entities.Person;
import facades.CityInfoFacade;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AddressDTO implements Serializable {
    private  String street;
    private  String additionalInfo;
    private List<PersonDTO> people;

    public AddressDTO(Address address) {
        if(address.getStreet()!= null)
            this.street = getStreet();
        this.additionalInfo = getAdditionalInfo();
        address.getPeople().forEach(person->this.people.add(new PersonDTO(person)));
    }

    public String getStreet() {
        return street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDTO entity = (AddressDTO) o;
        return Objects.equals(this.street, entity.street) &&
                Objects.equals(this.additionalInfo, entity.additionalInfo)&&
                Objects.equals(this.people, entity.people);

    }

    @Override
    public int hashCode() {
        return Objects.hash(street, additionalInfo,people);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + street + ", " +
                "additionalInfo = " + additionalInfo + ", " +
                "cityinfoZipcode = "  + ")";
    }
}
