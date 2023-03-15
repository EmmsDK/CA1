package facades;

import entities.Address;
import entities.CityInfo;
import entities.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import static org.junit.jupiter.api.Assertions.*;

class PersonFacadeTest {
    private static EntityManagerFactory emf;
    private static PersonFacade facade;

    @BeforeEach
    void setUp() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = (PersonFacade) PersonFacade.getPersonFacade(emf);
        EntityManager em = emf.createEntityManager();
        Address address = new Address("Testvej", "69");
        CityInfo ci = new CityInfo(1234, "TestCity");
        ci.addAddress(address);
        address.setCityInfo(ci);
        Person person = new Person("John", "Doe", "ostemand@ost.dk");
        person.setAddress(address);
        address.addPerson(person);
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    void tearDown() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNativeQuery("SET FOREIGN_KEY_CHECKS=0;").executeUpdate();
            em.createNativeQuery("TRUNCATE person;").executeUpdate();
            em.createNativeQuery("DELETE c FROM CityInfo c WHERE c.zipCode = 1234;").executeUpdate();
            em.createNativeQuery("DELETE a FROM Address a WHERE a.street = 'Testvej';").executeUpdate();
            em.createNativeQuery("SET FOREIGN_KEY_CHECKS=1;").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void getPersonFacade() {
        assertEquals(true, facade instanceof PersonFacade);
    }

    @Test
    void create() {
        EntityManager em = emf.createEntityManager();
        CityInfo ci = em.find(CityInfo.class, 1234);
        Address a = new Address("TestWay", "48");
        a.setCityInfo(ci);
        Person person = new Person("Dohn", "Joe", "mælkemand@mælk.dk");
        person.setAddress(a);
        System.out.println(facade.create(person).getAddress().getStreet());
        assertEquals("TestWay", person.getAddress().getStreet());
        System.out.println(em.find(Person.class, 2));
        assertEquals("Dohn", em.find(Person.class, 2).getFirstName());
    }

    @Test
    void getById() {
        EntityManager em = emf.createEntityManager();
        assertEquals("John", em.find(Person.class, 1).getFirstName());
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void testDelete() {
    }
}