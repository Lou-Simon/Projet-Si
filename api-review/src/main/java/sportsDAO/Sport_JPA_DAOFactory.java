package sportsDAO;

import donnees.Sport;
import donnees.Discipline;
import donnees.Sportif;

public class Sport_JPA_DAOFactory extends SportsDAOFactory{
	private DAO_JPA<Sport> daoSport = null;
	
	private DAO_JPA daoDiscipline = null;
    private DAO_JPA daoSportif = null;
    
    @Override
    public DAO<Sport> getDAOSport() throws DAOException {
        if (daoSport == null) daoSport = new DAO_JPA<Sport>();
        return daoSport;
    }

    @Override
    public DAO<Discipline> getDAODiscipline() throws DAOException {
        if (daoDiscipline == null) daoDiscipline = new DAO_JPA<Discipline>();
        return daoDiscipline;
    }
    
    @Override
    public DAO<Sportif> getDAOSportif() throws DAOException {
        if (daoSportif == null) daoSportif = new DAO_JPA<Sportif>();
        return daoSportif;
    }

}
