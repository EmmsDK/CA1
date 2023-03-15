package facades;

import entities.Address;
import entities.CityInfo;
import junit.framework.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class CityInfoFacadeTest {

    private static EntityManagerFactory emf;
    private static CityInfoFacade facade;

    @BeforeEach
    public void setUp() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = (CityInfoFacade) CityInfoFacade.getCityInfoFacade(emf);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new CityInfo(1234, "TestCity"));
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
            entityManager.createNativeQuery("TRUNCATE address;").executeUpdate();
            entityManager.createNativeQuery("DELETE c FROM CityInfo c WHERE c.zipCode = 1234;").executeUpdate();
            entityManager.createNativeQuery("DELETE c FROM CityInfo c WHERE c.zipCode = 1235;").executeUpdate();
            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=1;").executeUpdate();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Test
    void getCityInfoFacade() {
        assertNotNull(facade);
    }

    @Test
    void create() {
        CityInfo ci = facade.create(new CityInfo(1235, "TestCitay"));
        System.out.println(ci.getCity());
        assertEquals("TestCitay", ci.getCity());
    }

    @Test
    void createAddress() {
        EntityManager em = emf.createEntityManager();
        CityInfo ci = em.find(CityInfo.class, 1234);
        Address a = new Address("Testvej", "69");
        a.setCityInfo(ci);
        System.out.println(facade.createAddress(a).getStreet());
        System.out.println(a.getStreet());
        assertEquals("Testvej", em.find(Address.class, a.getStreet()).getStreet());
    }

    @Test
    void getByZipCode() {
        CityInfo ci = facade.getByZipCode(2900);
        assertEquals("Hellerup", ci.getCity());
        try {
            assertEquals("The CityInfo entity with zipCode: " + 0000 + " Was not found", facade.getByZipCode(0000));
            fail("Should have thrown an exception");
        }
        catch (Exception e) {
            String expectedMessage = "The CityInfo entity with zipCode: 0 Was not found";
            assertEquals(expectedMessage, e.getMessage(), "Exception message must be correct");
        }
        System.out.println(ci.getCity());
    }

    @Test
    void getAll() {
        System.out.println(facade.getAll().size());
        assertTrue(facade.getAll().size() >= 1300);
    }

    @Test
    void update() {
        CityInfo ci = facade.getByZipCode(1234);
        ci.setCity("TæstCity");
        facade.update(ci);
        assertEquals("TæstCity", facade.getByZipCode(1234).getCity());
        System.out.println(facade.getByZipCode(1234).getCity());
        try {
            assertEquals("No CityInfo can be updated when id is missing", facade.update(new CityInfo(0000, "TestCity")));
            fail("Should have thrown an exception");
        }
        catch (Exception e) {
            String expectedMessage = "No CityInfo can be updated when id is missing";
            assertEquals(expectedMessage, e.getMessage(), "Exception message must be correct");
        }
    }

    @Test
    void delete() {
        System.out.println(facade.delete(1234));
        try {
            assertEquals("Could not remove CityInfo with id: " + 1234, facade.delete(1234));
            fail("Should have thrown an exception");
        }
        catch (Exception e) {
            String expectedMessage = "Could not remove CityInfo with id: 1234";
            assertEquals(expectedMessage, e.getMessage(), "Exception message must be correct");
        }


    }
    @Test
    void delete2(){
        System.out.println(facade.delete("TestCity").getZipCode());
        try {
            assertEquals("Could not remove CityInfo with cityname: TestCity", facade.delete("TestCity"));
            fail("Should have thrown an exception");
        }
        catch (Exception e) {
            String expectedMessage = "Could not remove CityInfo with cityname: TestCity";
            assertEquals(expectedMessage, e.getMessage(), "Exception message must be correct");
        }
    }
}