package dtos;

import java.io.Serializable;
import java.util.Objects;

public class CityInfoDTO implements Serializable {
    private final Integer zipCode;
    private final String city;

    public CityInfoDTO(Integer zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityInfoDTO entity = (CityInfoDTO) o;
        return Objects.equals(this.zipCode, entity.zipCode) &&
                Objects.equals(this.city, entity.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, city);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + zipCode + ", " +
                "city = " + city + ")";
    }
}
