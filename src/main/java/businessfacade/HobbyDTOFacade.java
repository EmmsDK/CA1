package businessfacades;



import dtos.HobbyDTO;


import entities.Hobby;

import errorhandling.EntityNotFoundException;
import facades.HobbyFacade;
import facades.IDataFacade;

import utils.EMF_Creator;

import java.util.List;

public class HobbyDTOFacade implements IDataFacade<HobbyDTO> {
    private static IDataFacade<HobbyDTO> instance;
    private static IDataFacade<Hobby> hobbyFacade;

    //Private Constructor to ensure Singleton
    private HobbyDTOFacade() {}

    public static IDataFacade<HobbyDTO> getFacade() {
        if (instance == null) {
            hobbyFacade = HobbyFacade.getHobbyFacade(EMF_Creator.createEntityManagerFactory());
            instance = new HobbyDTOFacade();
        }
        return instance;
    }

    @Override
    public HobbyDTO create(HobbyDTO hobbyDTO) {
       Hobby h = hobbyDTO.getEntity();
        h = hobbyFacade.create(h);
        return new HobbyDTO(h);
    }

    @Override
    public HobbyDTO getByString(String fill) throws EntityNotFoundException {
        return null;
    }

    @Override
    public HobbyDTO getByInt(int id) throws EntityNotFoundException {
        return new HobbyDTO(hobbyFacade.getByInt(id));
    }

    @Override
    public List<HobbyDTO> getAll() {
        return HobbyDTO.toList(hobbyFacade.getAll());
    }

    @Override
    public HobbyDTO update(HobbyDTO hobbyDTO) throws EntityNotFoundException {
        Hobby h = hobbyFacade.update(hobbyDTO.getEntity());
        return new HobbyDTO(h);
    }

    @Override
    public HobbyDTO delete(int id) throws EntityNotFoundException {
        return new HobbyDTO(hobbyFacade.delete(id));
    }

    @Override
    public HobbyDTO delete(String id) throws EntityNotFoundException {
        return null;
    }
}
