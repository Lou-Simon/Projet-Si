package sportsDAO;

import donnees.Sport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DAO_JPA_Sport extends DAO<Sport> {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public DAO_JPA_Sport() throws DAOException {
        super();
        try {
            emf = Persistence.createEntityManagerFactory("SportsPU");
            em = emf.createEntityManager();
        } catch (Exception e) {
            throw new DAOException("Erreur lors de l'initialisation du DAO JPA");
        }
    }
    
    @Override
    public Sport find(int id) throws DAOException {
    	try {
    		Sport sport = em.find(Sport.class, id);
    		return sport;
    	} catch(Exception e) {
    		throw new DAOException("Problème technique (" + e.getMessage() + ")");
    	}
    }
    
    @Override
    public void create(Sport sport) throws DAOException {
    	EntityTransaction trans = null;
    	try {
    		trans = em.getTransaction();
    		trans.begin();
    		em.persist(sport);
    		trans.commit();	
    	} catch(Exception e) {
    		throw new DAOException("Problème technique (" + e.getMessage() + ")");
    	}
    }
    
    @Override
    public void update(Sport sport) throws DAOException {
    	EntityTransaction trans = null;
    	try {
    		trans = em.getTransaction();
    		trans.begin();
    		em.persist(sport);
    		trans.commit();	
    	} catch(Exception e) {
    		throw new DAOException("Problème technique (" + e.getMessage() + ")");
    	}
    }
    
    public void delete(Sport sport) throws DAOException {
    	EntityTransaction trans = null;
    	try {
    		trans = em.getTransaction();
    		trans.begin();
    		em.remove(sport);
    		trans.commit();	
    	} catch(Exception e) {
    		throw new DAOException("Problème technique (" + e.getMessage() + ")");
    	}
    }
}