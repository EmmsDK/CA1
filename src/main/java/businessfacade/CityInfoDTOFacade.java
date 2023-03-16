package businessfacades;



import dtos.CityInfoDTO;
import dtos.HobbyDTO;


import entities.CityInfo;
import entities.Hobby;

import errorhandling.EntityNotFoundException;
import facades.CityInfoFacade;
import facades.HobbyFacade;
import facades.IDataFacade;

import utils.EMF_Creator;

import java.util.List;

public class CityInfoDTOFacade implements IDataFacade<CityInfoDTO> {
    private static IDataFacade<CityInfoDTO> instance;
    private static IDataFacade<CityInfo> cityInfoFacade;

    //Private Constructor to ensure Singleton
    private CityInfoDTOFacade() {}

    public static IDataFacade<CityInfoDTO> getFacade() {
        if (instance == null) {
            cityInfoFacade = CityInfoFacade.getCityInfoFacade(EMF_Creator.createEntityManagerFactory());
            instance = new CityInfoDTOFacade();
        }
        return instance;
    }

    @Override
    public CityInfoDTO create(CityInfoDTO cityInfoDTO) {
        CityInfo h = cityInfoDTO.getEntity();
        h = cityInfoFacade.create(h);
        return new CityInfoDTO(h);
    }



    @Override
    public CityInfoDTO getByString(String fill) throws EntityNotFoundException {
        return null;
    }

    @Override
    public CityInfoDTO getByInt(int id) throws EntityNotFoundException {
        return new CityInfoDTO(cityInfoFacade.getByInt(id));
    }

    @Override
    public List<CityInfoDTO> getAll() {
        return CityInfoDTO.toList(cityInfoFacade.getAll());
    }


    @Override
    public CityInfoDTO update(CityInfoDTO cityinfoDTO) throws EntityNotFoundException {
        CityInfo ci = cityInfoFacade.update(cityinfoDTO.getEntity());
        return new CityInfoDTO(ci);
    }

    @Override
    public CityInfoDTO delete(int id) throws EntityNotFoundException {
        return new CityInfoDTO(cityInfoFacade.delete(id));
    }

    @Override
    public CityInfoDTO delete(String id) throws EntityNotFoundException {
        return null;
    }
}
