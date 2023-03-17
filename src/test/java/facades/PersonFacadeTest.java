package facades;

import entities.Address;
import entities.City;
import entities.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
        City c = new City(1234, "TestCity");
        c.addAddress(address);
        address.setCity(c);
        Person person = new Person("John", "Doe", "ostemand@ost.dk");
        person.setAddress(address);
        address.addPerson(person);
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.merge(person);
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
            em.createNativeQuery("DELETE c FROM City c WHERE c.zipCode = 1234;").executeUpdate();
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
        City c = em.find(City.class, 1234);
        Address a = new Address("TestWay", "48");
        a.setCity(c);
        Person person = new Person("Dohn", "Joe", "mælkemand@mælk.dk");
        person.setAddress(a);
        System.out.println(facade.create(person));
        assertNotEquals(null, em.find(Person.class, 2));
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