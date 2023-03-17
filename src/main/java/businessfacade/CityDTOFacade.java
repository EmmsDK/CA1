package businessfacade;



import dtos.CityDTO;


import entities.City;

import errorhandling.EntityNotFoundException;
import facades.CityFacade;
import facades.IDataFacade;

import utils.EMF_Creator;

import java.util.List;

public class CityDTOFacade implements IDataFacade<CityDTO> {
    private static IDataFacade<CityDTO> instance;
    private static IDataFacade<City> cityFacade;

    //Private Constructor to ensure Singleton
    private CityDTOFacade() {}

    public static IDataFacade<CityDTO> getFacade() {
        if (instance == null) {
            cityFacade = CityFacade.getCityFacade(EMF_Creator.createEntityManagerFactory());
            instance = new CityDTOFacade();
        }
        return instance;
    }

    @Override
    public CityDTO create(CityDTO cityDTO) {
        City c = cityDTO.getEntity();
        c = cityFacade.create(c);
        return new CityDTO(c);
    }



    @Override
    public CityDTO getByString(String fill) throws EntityNotFoundException {
        return null;
    }

    @Override
    public CityDTO getById(int id) throws EntityNotFoundException {
        return new CityDTO(cityFacade.getById(id));
    }

    @Override
    public List<CityDTO> getAll() {
        return CityDTO.toList(cityFacade.getAll());
    }


    @Override
    public CityDTO update(CityDTO cityDTO) throws EntityNotFoundException {
        City c = cityFacade.update(cityDTO.getEntity());
        return new CityDTO(c);
    }

    @Override
    public CityDTO delete(int id) throws EntityNotFoundException {
        return new CityDTO(cityFacade.delete(id));
    }

    @Override
    public CityDTO delete(String id) throws EntityNotFoundException {
        return null;
    }
}
