package sportsDAO;

import donnees.Discipline;
import donnees.Sport;
import donnees.Sportif;

/**
 * Fabrique abstraite de DAO pour le sch�ma sports
 * @author Eric
 */
public abstract class SportsDAOFactory {
    
    /**
     * @return le DAO pour la classe/table Sport.
     * @throws DAOException en cas de probl�me
     */
    public abstract DAO<Sport> getDAOSport() throws DAOException;
    
    /**
     * @return le DAO pour la classe/table Discipline.
     * @throws DAOException en cas de probl�me
     */
    public abstract DAO<Discipline> getDAODiscipline() throws DAOException;
    
    public abstract DAO<Sportif> getDAOSportif() throws DAOException;

}
