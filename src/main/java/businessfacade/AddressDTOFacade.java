package businessfacades;



import dtos.AddressDTO;
import dtos.CityInfoDTO;
import dtos.HobbyDTO;


import entities.Address;
import entities.CityInfo;
import entities.Hobby;

import errorhandling.EntityNotFoundException;
import facades.AddressFacade;
import facades.CityInfoFacade;
import facades.HobbyFacade;
import facades.IDataFacade;

import utils.EMF_Creator;

import java.util.List;

public class AddressDTOFacade implements IDataFacade<AddressDTO> {
    private static IDataFacade<AddressDTO> instance;
    private static IDataFacade<Address> addressFacade;

    //Private Constructor to ensure Singleton
    private AddressDTOFacade() {}

    public static IDataFacade<AddressDTO> getFacade() {
        if (instance == null) {
            addressFacade = AddressFacade.getAddressFacade(EMF_Creator.createEntityManagerFactory());
            instance = new AddressDTOFacade();
        }
        return instance;
    }

    @Override
    public AddressDTO create(AddressDTO addressDTO) {
        Address a = addressDTO.getEntity();
        a = addressFacade.create(a);
        return new AddressDTO(a);
    }



    @Override
    public AddressDTO getByString(String fill) throws EntityNotFoundException {
        return null;
    }

    @Override
    public AddressDTO getByInt(int id) throws EntityNotFoundException {
        return new AddressDTO(addressFacade.getByInt(id));
    }

    @Override
    public List<AddressDTO> getAll() {
        return AddressDTO.toList(addressFacade.getAll());
    }


    @Override
    public AddressDTO update(AddressDTO adto) throws EntityNotFoundException {
        Address a = addressFacade.update(adto.getEntity());
        return new AddressDTO(a);
    }

    @Override
    public AddressDTO delete(int id) throws EntityNotFoundException {
        return new AddressDTO(addressFacade.delete(id));
    }

    @Override
    public AddressDTO delete(String id) throws EntityNotFoundException {
        return null;
    }
}
