package businessfacades;



import dtos.PersonDTO;

import entities.Person;
import errorhandling.EntityNotFoundException;
import facades.IDataFacade;
import facades.PersonFacade;
import utils.EMF_Creator;

import java.util.List;

public class PersonDTOFacade implements IDataFacade<PersonDTO> {
    private static IDataFacade<PersonDTO> instance;
    private static IDataFacade<Person> personFacade;

    //Private Constructor to ensure Singleton
    private PersonDTOFacade() {}

    public static IDataFacade<PersonDTO> getFacade() {
        if (instance == null) {
            personFacade = PersonFacade.getPersonFacade(EMF_Creator.createEntityManagerFactory());
            instance = new PersonDTOFacade();
        }
        return instance;
    }

    @Override
    public PersonDTO create(PersonDTO personDTO) {
        Person p = personDTO.getEntity();
        p = personFacade.create(p);
        return new PersonDTO(p);
    }

    @Override
    public PersonDTO getByString(String fill) throws EntityNotFoundException {
        return null;
    }

    @Override
    public PersonDTO getByInt(int id) throws EntityNotFoundException {
        return new PersonDTO(personFacade.getByInt(id));
    }

    @Override
    public List<PersonDTO> getAll() {
        return PersonDTO.toList(personFacade.getAll());
    }

    @Override
    public PersonDTO update(PersonDTO personDTO) throws EntityNotFoundException {
        Person p = personFacade.update(personDTO.getEntity());
        return new PersonDTO(p);
    }

    @Override
    public PersonDTO delete(int id) throws EntityNotFoundException {
        return new PersonDTO(personFacade.delete(id));
    }

    @Override
    public PersonDTO delete(String id) throws EntityNotFoundException {
        return null;
    }
}
