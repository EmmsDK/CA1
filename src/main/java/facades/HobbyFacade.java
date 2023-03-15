package facades;

import entities.CityInfo;
import entities.Hobby;

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
public class HobbyFacade implements IDataFacade<Hobby> {

    private static HobbyFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private HobbyFacade() {
    }


    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static IDataFacade<Hobby> getHobbyFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyFacade();
        }
        return instance;
    }
    //mangler
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
/*
    public Hobby create(Hobby p) {
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
    public Hobby create(Hobby hobby) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return hobby;
    }

    public Hobby getByString(String fill) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Hobby h = em.find(Hobby.class, fill);
        if (h == null)
            throw new EntityNotFoundException("The Hobby entity with ID: " + fill + " Was not found");
        return h;
    }


    /*public Hobby getByNumber(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Hobby p = em.find(Hobby.class, id);
        if (p == null)
            throw new EntityNotFoundException("The Hobby entity with ID: " + id + " Was not found");
        return p;
    }*/

    @Override
    public List<Hobby> getAll() {
        EntityManager em = getEntityManager();
        TypedQuery<Hobby> query = em.createQuery("SELECT p FROM Hobby p", Hobby.class);
        List<Hobby> hobbies = query.getResultList();
        return hobbies;
    }

    @Override
    public Hobby update(Hobby hobby) throws EntityNotFoundException {
        if (hobby.getName() == null)
            throw new IllegalArgumentException("No Hobby can be updated when id is missing");
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Hobby h = em.merge(hobby);
        em.getTransaction().commit();
        return h;
    }

    @Override
    public Hobby delete(int id) throws errorhandling.EntityNotFoundException {
        return null;
    }


    @Override
    public Hobby delete(String name) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Hobby hobby = em.find(Hobby.class, name);
        if (hobby == null)
            throw new EntityNotFoundException("Could not remove CityInfo with name: " + name);
        em.getTransaction().begin();
        em.remove(hobby);
        em.getTransaction().commit();
        return hobby;
    }

       /* public static void main(String[] args) {
            emf = EMF_Creator.createEntityManagerFactory();
            IDataFacade fe = getHobbyFacade(emf);
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


