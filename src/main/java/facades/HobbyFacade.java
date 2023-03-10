package facades;

import entities.Hobby;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;

public class HobbyFacade {

    private static HobbyFacade instance;
    private static EntityManagerFactory emf;

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Hobby create(Hobby hobby) throws EntityNotFoundException {
        Hobby h = new Hobby(hobby.getName(),hobby.getDescription());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return h;
    }
}
