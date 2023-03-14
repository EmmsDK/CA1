package businessfacade;

import dtos.PhoneDTO;
import entities.Phone;
import errorhandling.EntityNotFoundException;
import facades.IDataFacade;
import facades.PhoneFacade;
import utils.EMF_Creator;

package businessfacades;

import datafacades.IDataFacade;
import datafacades.ParentFacade;
import dtos.ParentDTO;
import entities.Parent;
import errorhandling.EntityNotFoundException;
import utils.EMF_Creator;

import java.util.List;

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
            Phone p = phoneDTO.getEntity();
            p = phoneFacade.create(p);
            return new PhoneDTO(p);
        }

        @Override
        public ParentDTO getById(int id) throws EntityNotFoundException {
            return new ParentDTO(parentFacade.getById(id));
        }

        @Override
        public List<ParentDTO> getAll() {
            return ParentDTO.toList(parentFacade.getAll());
        }

        @Override
        public ParentDTO update(ParentDTO parentDTO) throws EntityNotFoundException {
            Parent p = parentFacade.update(parentDTO.getEntity());
            return new ParentDTO(p);
        }

        @Override
        public ParentDTO delete(int id) throws EntityNotFoundException {
            return new ParentDTO(parentFacade.delete(id));
        }
    }


