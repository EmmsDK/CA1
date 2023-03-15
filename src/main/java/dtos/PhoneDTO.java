package dtos;
/*
import entities.Phone;

import java.io.Serializable;
import java.util.Objects;

public class PhoneDTO implements Serializable {
    private final Integer number;
    private final String description;

    public PhoneDTO(Integer number, String description) {
        this.number = number;
        this.description = description;
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

    public Phone getEntity(){
        Phone p = new Phone(this.number, this.description);
        if(number != 0)
            p.setId(this.id);
        this.children.forEach(child->p.addChild(child.getEntity()));
        return p;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + number + ", " +
                "description = " + description + ")";
    }
}

 */