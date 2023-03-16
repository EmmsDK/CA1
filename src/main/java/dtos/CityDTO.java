package dtos;

import entities.Address;
import entities.City;

import entities.Hobby;
import entities.Person;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class CityDTO implements Serializable {
    private  Integer zipCode;
    private  String cityName;
    private List<AddressDTO> addresses;

    public CityDTO(City city) {

       if (city.getZipCode()!=null)
           this.zipCode = city.getZipCode();

        this.cityName = city.getCityName();
        city.getAddresses().forEach(address->this.addresses.add(new AddressDTO(address)));


    }

    public Integer getZipCode() {
        return zipCode;
    }

    public String getCityName() {
        return cityName;
    }

    public City getEntity() {
        City ci = new City(this.zipCode, this.cityName);
        if (zipCode != 0)
            ci.setZipCode(this.zipCode);
        this.addresses.forEach(address->ci.addAddress(address.getEntity()));
        return ci;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityDTO entity = (CityDTO) o;
        return Objects.equals(this.zipCode, entity.zipCode) &&
                Objects.equals(this.cityName, entity.cityName) &&
                Objects.equals(this.addresses, entity.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, cityName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + zipCode + ", " +
                "city = " + cityName + ")";
    }
    public static List<CityDTO> toList(List<City> citys) {
        return citys.stream().map(CityDTO::new).collect(Collectors.toList());
    }

}
