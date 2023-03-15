package businessfacades;



import dtos.HobbyDTO;


import dtos.PhoneDTO;
import entities.Hobby;

import entities.Phone;
import errorhandling.EntityNotFoundException;
import facades.HobbyFacade;
import facades.IDataFacade;

import facades.PhoneFacade;
import utils.EMF_Creator;

import java.util.List;

public class PhoneDTOFacade implements IDataFacade<PhoneDTO> {
    private static IDataFacade<PhoneDTO> instance;
    private static IDataFacade<Phone> phoneFacade;

    //Private Constructor to ensure Singleton
    private PhoneDTOFacade() {}

    public static IDataFacade<PhoneDTO> getFacade() {
        if (instance == null) {
            phoneFacade = PhoneFacade.getPhoneFacade(EMF_Creator.createEntityManagerFactory());
            instance = new PhoneDTOFacade();
        }
        return instance;
    }

    @Override
    public PhoneDTO create(PhoneDTO phoneDTO) {
        Phone p= phoneDTO.getEntity();
        p= phoneFacade.create(p);
        return new PhoneDTO(p);
    }

    @Override
    public PhoneDTO getByString(String fill) throws EntityNotFoundException {
        return null;
    }

    @Override
    public PhoneDTO getByInt(int id) throws EntityNotFoundException {
        return new PhoneDTO(phoneFacade.getByInt(id));
    }

    @Override
    public List<PhoneDTO> getAll() {
        return PhoneDTO.toList(phoneFacade.getAll());
    }

    @Override
    public PhoneDTO update(PhoneDTO phoneDTO) throws EntityNotFoundException {
        Phone p= phoneFacade.update(phoneDTO.getEntity());
        return new PhoneDTO(p);
    }

    @Override
    public PhoneDTO delete(int id) throws EntityNotFoundException {
        return new PhoneDTO(phoneFacade.delete(id));
    }

    @Override
    public PhoneDTO delete(String id) throws EntityNotFoundException {
        return null;
    }
}
