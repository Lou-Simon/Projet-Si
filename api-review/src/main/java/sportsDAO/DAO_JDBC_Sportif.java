package sportsDAO;

import donnees.Sportif;
import donnees.Discipline;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.Set;

/**
 * DAO pour la classe/table Sportif avec implémentation en JDBC.
  * @author Lou
 */
public class DAO_JDBC_Sportif extends DAO<Sportif> {
    
    private Connection connection = null;
    
    public DAO_JDBC_Sportif() throws DAOException {
        super();
        this.connection = SQLConnection.getConnection();
    }
    
    protected Set<Discipline> getDisciplinesSportif(Sportif sportif) throws Exception {
        Statement req = connection.createStatement();
        ResultSet res = req.executeQuery(
            "SELECT d.* FROM discipline d " +
            "INNER JOIN pratique p ON d.code_discipline = p.code_discipline " +
            "WHERE p.code_sportif = " + sportif.getCodeSportif()
        );
        
        HashSet<Discipline> disciplines = new HashSet<>();
        DAO<Discipline> discDAO = new DAO_JDBC_Discipline();
        
        while (res.next()) {
            Discipline disc = discDAO.find(res.getInt("code_discipline"));
            disciplines.add(disc);
        }
        return disciplines;
    }
    
    @Override
    public Sportif find(int id) throws DAOException {
        try {
            Statement req = connection.createStatement();
            ResultSet res = req.executeQuery("SELECT * FROM sportif WHERE code_sportif = " + id);
            
            if (res.next()) {
                Sportif sportif = new Sportif();
                sportif.setCodeSportif(res.getInt("code_sportif"));
                sportif.setNom(res.getString("nom"));
                sportif.setRue(res.getString("rue"));
                sportif.setVille(res.getString("ville"));
                sportif.setCodePostal(res.getString("code_postal"));
                sportif.setDisciplines(getDisciplinesSportif(sportif));
                
                return sportif;
            } else {
                throw new DAOException("Le sportif d'identifiant " + id + " n'existe pas");
            }
        } catch (Exception e) {
            throw new DAOException("Problème technique (" + e.getMessage() + ")");
        }
    }
    
    @Override
    public void create(Sportif sportif) throws DAOException {
        try {
            Statement req = connection.createStatement();
            ResultSet res = req.executeQuery("SELECT MAX(code_sportif) FROM sportif");
            res.next();
            int codeSportif = res.getInt(1) + 1;
            sportif.setCodeSportif(codeSportif);
            
            PreparedStatement reqParam = connection.prepareStatement(
                "INSERT INTO sportif VALUES (?, ?, ?, ?, ?)"
            );
            reqParam.setInt(1, sportif.getCodeSportif());
            reqParam.setString(2, sportif.getNom());
            reqParam.setString(3, sportif.getRue());
            reqParam.setString(4, sportif.getVille());
            reqParam.setString(5, sportif.getCodePostal());
            reqParam.executeUpdate();
            
            // Créer les associations dans la table pratique
            if (sportif.getDisciplines() != null) {
                for (Discipline disc : sportif.getDisciplines()) {
                    PreparedStatement reqPratique = connection.prepareStatement(
                        "INSERT INTO pratique VALUES (?, ?)"
                    );
                    reqPratique.setInt(1, sportif.getCodeSportif());
                    reqPratique.setInt(2, disc.getCodeDiscipline());
                    reqPratique.executeUpdate();
                }
            }
        } catch (Exception e) {
            throw new DAOException("Problème technique (" + e.getMessage() + ")");
        }
    }
    
    @Override
    public void update(Sportif sportif) throws DAOException {
        try {
            PreparedStatement reqParam = connection.prepareStatement(
                "UPDATE sportif SET nom = ?, rue = ?, ville = ?, code_postal = ? WHERE code_sportif = ?"
            );
            reqParam.setString(1, sportif.getNom());
            reqParam.setString(2, sportif.getRue());
            reqParam.setString(3, sportif.getVille());
            reqParam.setString(4, sportif.getCodePostal());
            reqParam.setInt(5, sportif.getCodeSportif());
            reqParam.executeUpdate();
            
            // Mettre à jour les associations dans pratique
            PreparedStatement reqDelete = connection.prepareStatement(
                "DELETE FROM pratique WHERE code_sportif = ?"
            );
            reqDelete.setInt(1, sportif.getCodeSportif());
            reqDelete.executeUpdate();
            
            if (sportif.getDisciplines() != null) {
                for (Discipline disc : sportif.getDisciplines()) {
                    PreparedStatement reqPratique = connection.prepareStatement(
                        "INSERT INTO pratique VALUES (?, ?)"
                    );
                    reqPratique.setInt(1, sportif.getCodeSportif());
                    reqPratique.setInt(2, disc.getCodeDiscipline());
                    reqPratique.executeUpdate();
                }
            }
        } catch (Exception e) {
            throw new DAOException("Problème technique (" + e.getMessage() + ")");
        }
    }
    
    @Override
    public void delete(Sportif sportif) throws DAOException {
        try {
            // Supprimer les associations dans pratique
            PreparedStatement reqParam = connection.prepareStatement(
                "DELETE FROM pratique WHERE code_sportif = ?"
            );
            reqParam.setInt(1, sportif.getCodeSportif());
            reqParam.executeUpdate();
            
            // Supprimer le sportif
            reqParam = connection.prepareStatement(
                "DELETE FROM sportif WHERE code_sportif = ?"
            );
            reqParam.setInt(1, sportif.getCodeSportif());
            reqParam.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("Problème technique (" + e.getMessage() + ")");
        }
    }
}