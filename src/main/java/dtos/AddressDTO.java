package dtos;

import facades.CityInfoFacade;

import java.io.Serializable;
import java.util.Objects;

public class AddressDTO implements Serializable {
    private final String street;
    private final String additionalInfo;
    private final CityInfoDTO cityInfoDTO;

    public AddressDTO(String street, String additionalInfo, CityInfoDTO cityInfoDTO) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityInfoDTO = cityInfoDTO;
    }

    public String getStreet() {
        return street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public CityInfoDTO getCityInfoDTO() {
        return cityInfoDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDTO entity = (AddressDTO) o;
        return Objects.equals(this.street, entity.street) &&
                Objects.equals(this.additionalInfo, entity.additionalInfo) &&
                Objects.equals(this.cityInfoDTO, entity.cityInfoDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, additionalInfo, cityInfoDTO);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + street + ", " +
                "additionalInfo = " + additionalInfo + ", " +
                "cityinfoZipcode = " + cityInfoDTO + ")";
    }
}
