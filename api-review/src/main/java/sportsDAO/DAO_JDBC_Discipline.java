package sportsDAO;

import donnees.Discipline;
import donnees.Sport;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * DAO pour la classe/table Discipline avec implémentation en JDBC.
 * @author Eric
 */
class DAO_JDBC_Discipline extends DAO<Discipline> {
    private Connection connection = null;
    
    public DAO_JDBC_Discipline() throws DAOException {
        super();
        this.connection = SQLConnection.getConnection();
    }
    
    @Override
    public Discipline find(int id) throws DAOException {
        try {
            Statement req = connection.createStatement();
            ResultSet res = req.executeQuery("SELECT * FROM discipline WHERE code_discipline = " + id);

            if (res.next()) {
                DAO<Sport> sportDAO = new DAO_JDBC_Sport();
                Sport sport = sportDAO.find(res.getInt("code_sport"));

                return new Discipline(res.getInt("code_discipline"), res.getString("intitule"), sport);
            }
            return null;
        } catch (Exception e) {
            throw new DAOException("Problème technique (" + e.getMessage() + ")");
        }
    }
    
    @Override
    public void create(Discipline disc) throws DAOException {
        try {
            Statement req = connection.createStatement();
            ResultSet res = req.executeQuery("SELECT MAX(code_discipline) FROM discipline");
            res.next();
            int codeDisc = res.getInt(1) + 1;
            disc.setCodeDiscipline(codeDisc);
            
            req.executeUpdate("INSERT INTO discipline VALUES (" + codeDisc + ", '" + disc.getIntitule() + "', " + disc.getSport().getCodeSport() + ")");
        } catch (Exception e) {
            throw new DAOException("Problème technique (" + e.getMessage() + ")");
        }
    }
    
    @Override
    public void update(Discipline disc) throws DAOException {
        try {
            Statement req = connection.createStatement();
            req.executeUpdate("UPDATE discipline SET intitule = '" + disc.getIntitule() + "', code_sport = " + disc.getSport().getCodeSport() + " WHERE code_discipline = " + disc.getCodeDiscipline());
        } catch (Exception e) {
            throw new DAOException("Problème technique (" + e.getMessage() + ")");
        }
    }
    
    @Override
    public void delete(Discipline disc) throws DAOException {
        try {
            Statement req = connection.createStatement();
            req.executeUpdate("DELETE FROM discipline WHERE code_discipline = " + disc.getCodeDiscipline());
        } catch (Exception e) {
            throw new DAOException("Problème technique (" + e.getMessage() + ")");
        }
    }
}