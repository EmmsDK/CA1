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

import static org.junit.jupiter.api.Assertions.*;

class PersonFacadeTest {
    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    @BeforeEach
    void setUp() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = (PersonFacade) PersonFacade.getPersonFacade(emf);
        EntityManager em = emf.createEntityManager();
        Address a = em.find(Address.class, 2900);
        try {
            em.getTransaction().begin();
            em.persist(new Person("John", "Doe", "ostemand@ost.dk", a));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    void tearDown() {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=0;").executeUpdate();
            entityManager.createNativeQuery("TRUNCATE person;").executeUpdate();
            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=1;").executeUpdate();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Test
    void getPersonFacade() {
        assertEquals(true, facade instanceof PersonFacade);
    }

    @Test
    void create() {
        EntityManager em = emf.createEntityManager();
        Address a = em.find(Address.class, 2900);
        Person person = facade.create(new Person("Dohn", "Joe", "mælkemand@mælk.dk", a));
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