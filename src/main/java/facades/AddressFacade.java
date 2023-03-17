package facades;

import entities.Address;
import entities.City;
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
public class AddressFacade implements IDataFacade<Address> {

    private static AddressFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private AddressFacade() {
    }


    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static IDataFacade<Address> getAddressFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AddressFacade();
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
    public Address create(Address address) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return address;
    }

    public Address getByString(String fill) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Address a = em.find(Address.class, fill);
        if (a == null)
            throw new EntityNotFoundException("The Address entity with name: " + fill + " Was not found");
        return a;
    }

    @Override
    public Address getById(int fill) throws errorhandling.EntityNotFoundException {
            EntityManager em = getEntityManager();
            Address a = em.find(Address.class, fill);
            if (a == null)
                throw new EntityNotFoundException("The Address entity with zipcode: " + fill + " Was not found");
            return a;
    }


    /*public Hobby getByNumber(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Hobby p = em.find(Hobby.class, id);
        if (p == null)
            throw new EntityNotFoundException("The Hobby entity with ID: " + id + " Was not found");
        return p;
    }*/

    @Override
    public List<Address> getAll() {
        EntityManager em = getEntityManager();
        TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a", Address.class);
        List<Address> address = query.getResultList();
        return address;
    }

    @Override
    public Address update(Address address) throws EntityNotFoundException {
        if (address.getStreet() == null)
            throw new IllegalArgumentException("No Hobby can be updated when id is missing");
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Address a = em.merge(address);
        em.getTransaction().commit();
        return a;
    }

    @Override
    public Address delete(int id) throws errorhandling.EntityNotFoundException {
        return null;
    }


    @Override
    public Address delete(String name) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Address a = em.find(Address.class, name);
        if (a == null)
            throw new EntityNotFoundException("Could not remove CityInfo with name: " + name);
        em.getTransaction().begin();
        em.remove(a);
        em.getTransaction().commit();
        return a;
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