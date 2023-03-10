package dtos;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class HobbyDTO implements Serializable {
    private final String name;
    private final String description;
    private final Set<PersonDTO> people;

    public HobbyDTO(String name, String description, Set<PersonDTO> people) {
        this.name = name;
        this.description = description;
        this.people = people;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HobbyDTO entity = (HobbyDTO) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + name + ", " +
                "description = " + description + ")";
    }

    public Set<PersonDTO> getPeople() {
        return people;
    }
}
