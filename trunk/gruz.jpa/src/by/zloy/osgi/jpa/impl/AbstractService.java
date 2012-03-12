package by.zloy.osgi.jpa.impl;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.jpa.osgi.PersistenceProvider;

public abstract class AbstractService {

	private static final String PU_NAME = "gruz";
	protected EntityManagerFactory emf;
    protected EntityManager em;
    
	public AbstractService() {
		begin();
	}

    public void commit() {
        EntityTransaction transaction = getEntityManager().getTransaction();
        if (transaction.isActive()) {
            transaction.commit();            
        } else {
            throw new RuntimeException("TX not active");
        }
        begin();
    }
    
	public void dispose() {
        if (getEntityManager().getTransaction().isActive()) {
            getEntityManager().getTransaction().rollback();
        }
        getEntityManagerFactory().close();
    }
	
	public void begin() {
		EntityTransaction transaction = getEntityManager().getTransaction();
        if (transaction.isActive()) {
            throw new RuntimeException("TX already active");
        } else {
            transaction.begin();            
        }
    }
	
	public EntityManager getEntityManager() {
        if (em == null) {
            em = getEntityManagerFactory().createEntityManager();
        }
        return em;
    }
	
	private EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            HashMap<String, ClassLoader> properties = new HashMap<String, ClassLoader>();
            properties.put(PersistenceUnitProperties.CLASSLOADER, this.getClass().getClassLoader());
            emf = new PersistenceProvider().createEntityManagerFactory(PU_NAME, properties);
        }
        return emf;
    }
}
