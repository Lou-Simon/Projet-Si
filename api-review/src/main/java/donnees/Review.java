package donnees;

import java.time.LocalDateTime;

public class Review {

    private String id;
    private String filmId;
    private String utilisateurId;
    private Float note;
    private String titre;
    private String commentaire;
    private LocalDateTime datePublication;

    public Review() {
    }

    public Review(String id, String filmId, String utilisateurId, Float note, String titre, String commentaire, LocalDateTime datePublication) {
        this.id = id;
        this.filmId = filmId;
        this.utilisateurId = utilisateurId;
        this.note = note;
        this.titre = titre;
        this.commentaire = commentaire;
        this.datePublication = datePublication;
    }

    public String getId() {
        return id;
    }

    public String getFilmId() {
        return filmId;
    }

    public String getUtilisateurId() {
        return utilisateurId;
    }

    public Float getNote() {
        return note;
    }

    public String getTitre() {
        return titre;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public LocalDateTime getDatePublication() {
        return datePublication;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public void setUtilisateurId(String utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setDatePublication(LocalDateTime datePublication) {
        this.datePublication = datePublication;
    }
}