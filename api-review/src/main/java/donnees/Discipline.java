package donnees;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * POJO Discipline
 * @author Eric
 */
@Entity
@Table(name = "discipline")
@NamedQueries({
    @NamedQuery(name = "Discipline.findAll", query = "SELECT d FROM Discipline d"),
    @NamedQuery(name = "Discipline.findByCodeDiscipline", query = "SELECT d FROM Discipline d WHERE d.codeDiscipline = :codeDiscipline"),
    @NamedQuery(name = "Discipline.findByIntitule", query = "SELECT d FROM Discipline d WHERE d.intitule = :intitule")})
public class Discipline implements java.io.Serializable {
    
    private static final long serialVersionUID = -2479329647238860078L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "code_discipline")
    private int codeDiscipline;
    
    @Column(name = "intitule")
    private String intitule;
    
    @JoinColumn(name = "code_sport", referencedColumnName = "code_sport")
    @ManyToOne
    private Sport sport;
    
    @ManyToMany(mappedBy = "disciplines")
    private Set<Sportif> sportifs;
    
    public Discipline() {
        this.sportifs = new HashSet<Sportif>();
    }
    
    public Discipline(int codeDiscipline, String intitule, Sport sport) {
        this.codeDiscipline = codeDiscipline;
        this.intitule = intitule;
        this.sport = sport;
        this.sportifs = new HashSet<Sportif>();
    }
    
    // Getters
    public int getCodeDiscipline() {
        return codeDiscipline;
    }
    
    public String getIntitule() {
        return intitule;
    }
    
    public Sport getSport() {
        return sport;
    }
    
    public Set<Sportif> getSportifs() {
        return sportifs;
    }
    
    // Setters
    public void setCodeDiscipline(int codeDiscipline) {
        this.codeDiscipline = codeDiscipline;
    }
    
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    
    public void setSport(Sport sport) {
        this.sport = sport;
    }
    
    public void setSportifs(Set<Sportif> sportifs) {
        this.sportifs = sportifs;
    }
    
    // Méthodes de gestion des sportifs
    public void addSportif(Sportif sportif) {
        this.sportifs.add(sportif);
        sportif.addDiscipline(this);
    }
    
    public void removeSportif(Sportif sportif) {
        this.sportifs.remove(sportif);
        sportif.removeDiscipline(this);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.codeDiscipline;
        hash = 89 * hash + Objects.hashCode(this.intitule);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Discipline other = (Discipline) obj;
        if (this.codeDiscipline != other.codeDiscipline) {
            return false;
        }
        if (!Objects.equals(this.intitule, other.intitule)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Discipline{" + "codeDiscipline=" + codeDiscipline + 
               ", intitule=" + intitule + ", sport=" + 
               (sport != null ? sport.getIntitule() : "null") + '}';
    }
}