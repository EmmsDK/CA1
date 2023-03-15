package dtos;

import entities.Address;
import entities.CityInfo;
import entities.Person;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CityInfoDTO implements Serializable {
    private  Integer zipCode;
    private  String city;
    private List<AddressDTO> addresses;

    public CityInfoDTO(CityInfo cityInfo) {

       if (cityInfo.getZipCode()!=null)
           this.zipCode = cityInfo.getZipCode();

        this.city = getCity();
        cityInfo.getAddresses().forEach(address->this.addresses.add(new AddressDTO(address)));

    }

    public Integer getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public CityInfo getEntity() {
        CityInfo ci = new CityInfo(this.zipCode, this.city);
        if (zipCode != 0)
            ci.setZipCode(this.zipCode);
        this.addresses.forEach(address->ci.addAddress(address.getEntity()));
        return ci;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityInfoDTO entity = (CityInfoDTO) o;
        return Objects.equals(this.zipCode, entity.zipCode) &&
                Objects.equals(this.city, entity.city) &&
                Objects.equals(this.addresses, entity.addresses);
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
