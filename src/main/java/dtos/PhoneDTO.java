package dtos;

import entities.Phone;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PhoneDTO implements Serializable {
    private  Integer number;
    private  String description;

    public PhoneDTO(Phone phone) {
        if(phone.getNumber() != null)
            this.number = phone.getNumber();

        this.description = phone.getDescription();
    }

    public Integer getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneDTO entity = (PhoneDTO) o;
        return Objects.equals(this.number, entity.number) &&
                Objects.equals(this.description, entity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, description);
    }

    public static List<PhoneDTO> toList(List<Phone> phones) {
        return phones.stream().map(PhoneDTO::new).collect(Collectors.toList());
    }

    public Phone getEntity(){
        Phone p = new Phone(this.number, this.description);
        if(number != 0)
            p.setNumber(this.number);
        return p;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + number + ", " +
                "description = " + description + ")";
    }
}

