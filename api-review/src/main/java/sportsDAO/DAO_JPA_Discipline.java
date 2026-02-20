package sportsDAO;

import donnees.Discipline;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DAO_JPA_Discipline extends DAO<Discipline> {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public DAO_JPA_Discipline() throws DAOException {
        super();
        try {
            emf = Persistence.createEntityManagerFactory("SportsPU");
            em = emf.createEntityManager();
        } catch (Exception e) {
            throw new DAOException("Erreur lors de l'initialisation du DAO JPA");
        }
    }
    
    @Override
    public Discipline find(int id) throws DAOException {
    	try {
    		Discipline discipline = em.find(Discipline.class, id);
    		return discipline;
    	} catch(Exception e) {
    		throw new DAOException("Problème technique (" + e.getMessage() + ")");
    	}
    }
    
    @Override
    public void create(Discipline discipline) throws DAOException {
    	EntityTransaction trans = null;
    	try {
    		trans = em.getTransaction();
    		trans.begin();
    		em.persist(discipline);
    		trans.commit();	
    	} catch(Exception e) {
    		throw new DAOException("Problème technique (" + e.getMessage() + ")");
    	}
    }
    
    public void update(Discipline discipline) throws DAOException {
    	EntityTransaction trans = null;
    	try {
    		trans = em.getTransaction();
    		trans.begin();
    		em.persist(discipline);
    		trans.commit();	
    	} catch(Exception e) {
    		throw new DAOException("Problème technique (" + e.getMessage() + ")");
    	}
    }
    
    public void delete(Discipline discipline) throws DAOException {
    	EntityTransaction trans = null;
    	try {
    		trans = em.getTransaction();
    		trans.begin();
    		em.remove(discipline);
    		trans.commit();	
    	} catch(Exception e) {
    		throw new DAOException("Problème technique (" + e.getMessage() + ")");
    	}
    }
}