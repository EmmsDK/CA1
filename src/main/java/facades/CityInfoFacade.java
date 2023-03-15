package facades;

import entities.Address;
import entities.CityInfo;

import javax.persistence.*;
import java.util.List;

/**
 * created by THA
 * Purpose of this facade example is to show a facade used as a DB facade (only operating on entity classes - no DTOs
 * And to show case some different scenarios
 */
public class CityInfoFacade implements IDataFacade<CityInfo> {

    private static CityInfoFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CityInfoFacade() {
    }


    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static IDataFacade<CityInfo> getCityInfoFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CityInfoFacade();
        }
        return instance;
    }
    //mangler
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
/*
    public CityInfo create(CityInfo p) {
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
    public CityInfo create(CityInfo ci) throws EntityNotFoundException {
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

    public Address createAddress(Address address) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        int zipCode = address.getCityInfo().getZipCode();
        String city = em.find(CityInfo.class, zipCode).getCity();
        address.setCityInfo(new CityInfo(zipCode, city));
        try {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return address;
    }
    
    public CityInfo getByZipCode(int zipCode) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        CityInfo ci = em.find(CityInfo.class, zipCode);
        if (ci == null)
            throw new EntityNotFoundException("The CityInfo entity with zipCode: " + zipCode + " Was not found");
        return ci;
    }



    @Override
    public List<CityInfo> getAll() {
        EntityManager em = getEntityManager();
        TypedQuery<CityInfo> query = em.createQuery("SELECT p FROM CityInfo p", CityInfo.class);
        List<CityInfo> cis = query.getResultList();
        return cis;
    }

    @Override
    public CityInfo update(CityInfo ci) throws EntityNotFoundException {
        if (ci.getZipCode() == 0)
            throw new IllegalArgumentException("No CityInfo can be updated when id is missing");
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        CityInfo p = em.merge(ci);
        em.getTransaction().commit();
        return p;
    }

    @Override
    public CityInfo delete(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        CityInfo ci = em.find(CityInfo.class, id);
        if (ci == null)
            throw new EntityNotFoundException("Could not remove CityInfo with id: " + id);
        em.getTransaction().begin();
        em.remove(ci);
        em.getTransaction().commit();
        return ci;
    }

    @Override
    public CityInfo delete(String id) throws errorhandling.EntityNotFoundException {
        return null;
    }

       /* public static void main(String[] args) {
            emf = EMF_Creator.createEntityManagerFactory();
            IDataFacade fe = getCityInfoFacade(emf);
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


