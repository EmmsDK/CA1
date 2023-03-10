package facades;

import entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * created by THA
 * Purpose of this facade example is to show a facade used as a DB facade (only operating on entity classes - no DTOs
 * And to show case some different scenarios
 */
public class PersonFacade implements IDataFacade<Person> {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }


    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static IDataFacade<Person> getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }
    //mangler
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
/*
    public Person create(Person p) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            p.getChildren().forEach(child -> {
                if (child.getId() != 0)
                    child = em.find(Child.class, child.getId());
                else {
                    child.getToys().forEach(toy -> {
                        if (toy.getId() != 0)
                            toy = em.find(Toy.class, toy.getId());
                        else {
                            em.persist(toy);
                        }
                    });
                    em.persist(child);
                }
            });
            em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return p;
    }
    */


    @Override
    public Person create(Person person) {
        return null;
    }

    @Override
    public Person getById(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, id);
        if (p == null)
            throw new EntityNotFoundException("The Person entity with ID: " + id + " Was not found");
        return p;
    }

    @Override
    public List<Person> getAll() {
        EntityManager em = getEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons = query.getResultList();
        return persons;
    }

    @Override
    public Person update(Person person) throws EntityNotFoundException {
        if (person.getId() == 0)
            throw new IllegalArgumentException("No Person can be updated when id is missing");
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Person p = em.merge(person);
        em.getTransaction().commit();
        return p;
    }

    @Override
    public Person delete(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, id);
        if (p == null)
            throw new EntityNotFoundException("Could not remove Person with id: " + id);
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        return p;
    }

       /* public static void main(String[] args) {
            emf = EMF_Creator.createEntityManagerFactory();
            IDataFacade fe = getPersonFacade(emf);
            fe.getAll().forEach(dto->System.out.println(dto));
        }
*/
/*
        //TODO Remove/Change this before use
        public long getRenameMeCount(){
            EntityManager em = getEntityManager();
            try{
                long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
                return renameMeCount;
            }finally{
                em.close();
            }
        }
*/

}


