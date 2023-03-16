package facades;

import entities.Address;
import entities.City;

import javax.persistence.*;
import java.util.List;

/**
 * created by THA
 * Purpose of this facade example is to show a facade used as a DB facade (only operating on entity classes - no DTOs
 * And to show case some different scenarios
 */
public class CityFacade implements IDataFacade<City> {

    private static CityFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CityFacade() {
    }


    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static IDataFacade<City> getCityFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CityFacade();
        }
        return instance;
    }
    //mangler
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
/*
    public City create(City p) {
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
    public City create(City ci) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ci);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return ci;
    }

    @Override
    public City getByString(String fill) throws errorhandling.EntityNotFoundException {
        return null;
    }

    @Override
    public City getByInt(int fill) throws errorhandling.EntityNotFoundException {
        return null;
    }

    public Address createAddress(Address address) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        int zipCode = address.getCity().getZipCode();
        String city = em.find(City.class, zipCode).getCityName();
        address.setCity(new City(zipCode, city));
        try {
            em.getTransaction().begin();
            em.merge(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return address;
    }
    
    public City getByZipCode(int zipCode) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        City ci = em.find(City.class, zipCode);
        if (ci == null)
            throw new EntityNotFoundException("The City entity with zipCode: " + zipCode + " Was not found");
        return ci;
    }



    @Override
    public List<City> getAll() {
        EntityManager em = getEntityManager();
        TypedQuery<City> query = em.createQuery("SELECT p FROM City p", City.class);
        List<City> cis = query.getResultList();
        return cis;
    }

    @Override
    public City update(City ci) throws EntityNotFoundException {
        if (ci.getZipCode() == 0)
            throw new IllegalArgumentException("No City can be updated when id is missing");
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        City p = em.merge(ci);
        em.getTransaction().commit();
        return p;
    }

    @Override
    public City delete(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        City ci = em.find(City.class, id);
        if (ci == null)
            throw new EntityNotFoundException("Could not remove City with id: " + id);
        em.getTransaction().begin();
        em.remove(ci);
        em.getTransaction().commit();
        return ci;
    }

    @Override
    public City delete(String city) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        TypedQuery<City> cityTypedQuery = em.createQuery("SELECT c FROM City c WHERE c.cityName = :city", City.class);
        try {
            City ci = cityTypedQuery.setParameter("city", city).getSingleResult();
            em.getTransaction().begin();
            em.remove(ci);
            em.getTransaction().commit();
            return ci;
        } catch (NoResultException e) {
            throw new EntityNotFoundException("Could not remove City with cityname: " + city);
        }

    }

       /* public static void main(String[] args) {
            emf = EMF_Creator.createEntityManagerFactory();
            IDataFacade fe = getInfoFacade(emf);
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


