package com.example.projet_nf28;

public class Offre {
    int id;
    String titre;
    String description;
    int argent;
    int nbCandidate;
    String recurrence;
    String durre;
    String adresse;
    String typeOffre;

    public int getId() {
        return id;
    }

    public int getArgent() {
        return argent;
    }

    public int getNbCandidate() {
        return nbCandidate;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getDescription() {
        return description;
    }

    public String getDurre() {
        return durre;
    }

    public String getRecurrence() {
        return recurrence;
    }

    public String getTitre() {
        return titre;
    }

    public String getTypeOffre() {
        return typeOffre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDurre(String durre) {
        this.durre = durre;
    }

    public void setNbCandidate(int nbCandidate) {
        this.nbCandidate = nbCandidate;
    }

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setTypeOffre(String typeOffre) {
        this.typeOffre = typeOffre;
    }
}
