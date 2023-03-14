package facades;

import entities.Address;
import entities.CityInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CityInfoFacadeTest {

    private static EntityManagerFactory emf;
    private static CityInfoFacade facade;

    @BeforeEach
    public void setUp() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = (CityInfoFacade) CityInfoFacade.getCityInfoFacade(emf);
    }

    @AfterEach
    void tearDown() {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=0;").executeUpdate();
            entityManager.createNativeQuery("TRUNCATE address;").executeUpdate();
            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=1;").executeUpdate();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Test
    void getCityInfoFacade() {
    }

    @Test
    void create() {
    }

    @Test
    void createAddress() {
        EntityManager em = emf.createEntityManager();
        Address a = new Address("Testvej", "69", 2900);
        facade.createAddress(a);
        System.out.println(em.find(CityInfo.class, 2900).getAddresses());
        assertEquals(1, em.find(CityInfo.class, 2900).getAddresses().size());
    }

    @Test
    void getByZipCode() {
        CityInfo ci = facade.getByZipCode(2900);
        assertEquals("Hellerup", ci.getCity());
        System.out.println(ci.getCity());
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