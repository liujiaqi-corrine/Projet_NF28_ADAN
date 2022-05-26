package com.example.projet_nf28;

public class Artiste extends User{
    String profession;
    String niveau;
    String cv; //lien ?
    String oevre; //lien ?

    Artiste(){}
    Artiste(User usr){
        this.id = usr.getId();
        this.nom = usr.getNom();
        this.prenom = usr.getPrenom();
        this.isArtiste = usr.getIsArtiste();
        this.isEmployer = usr.getIsEmployer();
    }
    Artiste(User usr,String profession,String niveau,String cv, String oevre){
        this.id = usr.getId();
        this.nom = usr.getNom();
        this.prenom = usr.getPrenom();
        this.isArtiste = usr.getIsArtiste();
        this.isEmployer = usr.getIsEmployer();
        this.profession = profession;
        this.niveau = niveau;
        this.cv = cv;
        this.oevre = oevre;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public void setOevre(String oevre) {
        this.oevre = oevre;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfession() {
        return profession;
    }

    public String getOevre() {
        return oevre;
    }

    public String getNiveau() {
        return niveau;
    }

    public String getCv() {
        return cv;
    }
}
