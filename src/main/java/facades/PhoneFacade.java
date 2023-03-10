package facades;

import entities.Phone;

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
public class PhoneFacade implements IDataFacade<Phone> {

    private static PhoneFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PhoneFacade() {
    }


    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static IDataFacade<Phone> getPhoneFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PhoneFacade();
        }
        return instance;
    }
    //mangler
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
/*
    public Phone create(Phone p) {
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
    @Override
    public Person create(Person person) throws EntityNotFoundException {
        Person p = new Person(person.getFirstName(),person.getLastName(),person.getEmail(),person.getAddressStreet());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return p;
    }
    */

    @Override
    public Phone create(Phone phone) throws EntityNotFoundException{
        Phone p = new Phone(phone.getNumber(),phone.getPerson(),phone.getDescription());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return null;
    }

    public Phone getByInt(int fill) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Phone p = em.find(Phone.class, fill);
        if (p == null)
            throw new EntityNotFoundException("The Phone entity with ID: " + fill + " Was not found");
        return p;
    }



    @Override
    public List<Phone> getAll() {
        EntityManager em = getEntityManager();
        TypedQuery<Phone> query = em.createQuery("SELECT p FROM Phone p", Phone.class);
        List<Phone> phones = query.getResultList();
        return phones;
    }

    @Override
    public Phone update(Phone phone) throws EntityNotFoundException {
        if (phone.getNumber() == 0)
            throw new IllegalArgumentException("No Phone can be updated when id is missing");
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Phone p = em.merge(phone);
        em.getTransaction().commit();
        return p;
    }

    @Override
    public Phone delete(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Phone p = em.find(Phone.class, id);
        if (p == null)
            throw new EntityNotFoundException("Could not remove Phone with id: " + id);
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        return p;
    }

    @Override
    public Phone delete(String id) throws errorhandling.EntityNotFoundException {
        return null;
    }

       /* public static void main(String[] args) {
            emf = EMF_Creator.createEntityManagerFactory();
            IDataFacade fe = getPhoneFacade(emf);
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


