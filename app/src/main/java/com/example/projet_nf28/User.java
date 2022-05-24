package com.example.projet_nf28;

public class User {
    int id;
    String nom;
    String prenom;
    String email;
    int isArtiste;
    int isEmployer;
    String mdp;

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIsArtiste(int isArtiste) {
        this.isArtiste = isArtiste;
    }

    public void setIsEmployer(int isEmployer) {
        this.isEmployer = isEmployer;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public int getIsArtiste() {
        return isArtiste;
    }

    public int getIsEmployer() {
        return isEmployer;
    }

    public String getEmail() {
        return email;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMdp() {
        return mdp;
    }
}
