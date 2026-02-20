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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * POJO Sportif
 */
@Entity
@Table(name = "sportif")
@NamedQueries({
    @NamedQuery(name = "Sportif.findAll", query = "SELECT s FROM Sportif s"),
    @NamedQuery(name = "Sportif.findByCodeSportif", query = "SELECT s FROM Sportif s WHERE s.codeSportif = :codeSportif"),
    @NamedQuery(name = "Sportif.findByNom", query = "SELECT s FROM Sportif s WHERE s.nom = :nom"),
    @NamedQuery(name = "Sportif.findByRue", query = "SELECT s FROM Sportif s WHERE s.rue = :rue"),
    @NamedQuery(name = "Sportif.findByVille", query = "SELECT s FROM Sportif s WHERE s.ville = :ville"),
    @NamedQuery(name = "Sportif.findByCodePostal", query = "SELECT s FROM Sportif s WHERE s.codePostal = :codePostal")})
public class Sportif implements java.io.Serializable {
    
    private static final long serialVersionUID = -3479329647238860078L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "code_sportif")
    private int codeSportif;
    
    @Column(name = "nom")
    private String nom;
    
    @Column(name = "rue")
    private String rue;
    
    @Column(name = "ville")
    private String ville;
    
    @Column(name = "code_postal")
    private String codePostal;
    
    @JoinTable(name = "pratique", 
        joinColumns = {
            @JoinColumn(name = "code_sportif", referencedColumnName = "code_sportif")}, 
        inverseJoinColumns = {
            @JoinColumn(name = "code_discipline", referencedColumnName = "code_discipline")})
    @ManyToMany
    private Set<Discipline> disciplines;
    
    public Sportif() {
        this.disciplines = new HashSet<Discipline>();
    }
    
    public Sportif(int codeSportif, String nom, String rue, String ville, String codePostal) {
        this.codeSportif = codeSportif;
        this.nom = nom;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.disciplines = new HashSet<Discipline>();
    }
    
    // Getters
    public int getCodeSportif() {
        return codeSportif;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String getRue() {
        return rue;
    }
    
    public String getVille() {
        return ville;
    }
    
    public String getCodePostal() {
        return codePostal;
    }
    
    public Set<Discipline> getDisciplines() {
        return disciplines;
    }
    
    // Setters
    public void setCodeSportif(int codeSportif) {
        this.codeSportif = codeSportif;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setRue(String rue) {
        this.rue = rue;
    }
    
    public void setVille(String ville) {
        this.ville = ville;
    }
    
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    
    public void setDisciplines(Set<Discipline> disciplines) {
        this.disciplines = disciplines;
    }
    
    // Méthodes de gestion des disciplines
    public void addDiscipline(Discipline discipline) {
        this.disciplines.add(discipline);
    }
    
    public void removeDiscipline(Discipline discipline) {
        this.disciplines.remove(discipline);
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.codeSportif;
        hash = 53 * hash + Objects.hashCode(this.nom);
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
        final Sportif other = (Sportif) obj;
        if (this.codeSportif != other.codeSportif) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Sportif{" + "codeSportif=" + codeSportif + 
               ", nom=" + nom + ", ville=" + ville + '}';
    }
}