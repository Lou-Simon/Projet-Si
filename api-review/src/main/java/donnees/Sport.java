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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * POJO Sport
 * @author Eric
 */
@Entity
@Table(name = "sport")
@NamedQueries({
    @NamedQuery(name = "Sport.findAll", query = "SELECT s FROM Sport s"),
    @NamedQuery(name = "Sport.findByCodeSport", query = "SELECT s FROM Sport s WHERE s.codeSport = :codeSport"),
    @NamedQuery(name = "Sport.findByIntitule", query = "SELECT s FROM Sport s WHERE s.intitule = :intitule")})
public class Sport implements java.io.Serializable {
    
    private static final long serialVersionUID = -1479329647238860078L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "code_sport")
    private int codeSport;
    
    @Column(name = "intitule")
    private String intitule;
    
    @OneToMany(mappedBy = "sport")
    private Set<Discipline> disciplines;
    
    public Sport() {
        this.disciplines = new HashSet<Discipline>();
    }
    
    public Sport(int codeSport, String intitule, Set<Discipline> disciplines) {
        this.codeSport = codeSport;
        this.intitule = intitule;
        this.disciplines = disciplines;
    }
    
    // Getters
    public int getCodeSport() {
        return codeSport;
    }
    
    public String getIntitule() {
        return intitule;
    }
    
    public Set<Discipline> getDisciplines() {
        return disciplines;
    }
    
    // Setters
    public void setCodeSport(int codeSport) {
        this.codeSport = codeSport;
    }
    
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    
    public void setDisciplines(Set<Discipline> disciplines) {
        this.disciplines = disciplines;
    }
    
    // Méthodes de gestion des disciplines
    public void addDiscipline(Discipline disc) {
        this.disciplines.add(disc);
    }
    
    public void removeDiscipline(Discipline disc) {
        this.disciplines.remove(disc);
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.codeSport;
        hash = 67 * hash + Objects.hashCode(this.intitule);
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
        final Sport other = (Sport) obj;
        if (this.codeSport != other.codeSport) {
            return false;
        }
        if (!Objects.equals(this.intitule, other.intitule)) {
            return false;
        }
        if (!Objects.equals(this.disciplines, other.disciplines)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Sport{" + "codeSport=" + codeSport + ", intitule=" + intitule + '}';
    }
}