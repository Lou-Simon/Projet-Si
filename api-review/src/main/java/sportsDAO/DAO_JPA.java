package sportsDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class DAO_JPA<D> extends DAO<D>{
	private EntityManagerFactory emf;
	private EntityManager em;
	private Class<D> classeEntite;

	public DAO_JPA() throws DAOException {
		super();
		try {
			emf = Persistence.createEntityManagerFactory("SportsPU");
			em = emf.createEntityManager();
		} catch (Exception e) {
			throw new DAOException("Erreur lors de l'initialisation du DAO JPA");
		}
	}
	
	@Override
	public D find(int id) throws DAOException {
    	try {
    		D data = em.find(classeEntite, id);
    		return data;
    	} catch(Exception e) {
    		throw new DAOException("Problème technique (" + e.getMessage() + ")");
    	}
    }

	@Override
	public void create(D data) throws DAOException {
		EntityTransaction trans = null;
		try {
			trans = em.getTransaction();
			trans.begin();
			em.persist(data);
			trans.commit();
		} catch (Exception e) {
			throw new DAOException("Problème technique (" + e.getMessage() + ")");
		}
	}

	@Override
	public void update(D data) throws DAOException {
		this.create(data);
	}
	
	@Override
	public void delete(D data) throws DAOException {
    	EntityTransaction trans = null;
    	try {
    		trans = em.getTransaction();
    		trans.begin();
    		em.remove(data);
    		trans.commit();	
    	} catch(Exception e) {
    		throw new DAOException("Problème technique (" + e.getMessage() + ")");
    	}
    }
}
