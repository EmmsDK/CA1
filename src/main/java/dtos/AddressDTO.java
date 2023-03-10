package dtos;

import java.io.Serializable;
import java.util.Objects;

public class AddressDTO implements Serializable {
    private final String street;
    private final String additionalInfo;
    private final CityInfoDTO cityinfoZipcode;

    public AddressDTO(String street, String additionalInfo, CityInfoDTO cityinfoZipcode) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityinfoZipcode = cityinfoZipcode;
    }

    public String getStreet() {
        return street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public CityInfoDTO getCityinfoZipcode() {
        return cityinfoZipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDTO entity = (AddressDTO) o;
        return Objects.equals(this.street, entity.street) &&
                Objects.equals(this.additionalInfo, entity.additionalInfo) &&
                Objects.equals(this.cityinfoZipcode, entity.cityinfoZipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, additionalInfo, cityinfoZipcode);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + street + ", " +
                "additionalInfo = " + additionalInfo + ", " +
                "cityinfoZipcode = " + cityinfoZipcode + ")";
    }
}
