package com.example.projet_nf28;

public class Employer extends User{
    String type;
    String certificat;

    Employer(){}
    Employer(User usr){
        this.id = usr.getId();
        this.nom = usr.getNom();
        this.prenom = usr.getPrenom();
        this.isArtiste = usr.getIsArtiste();
        this.isEmployer = usr.getIsEmployer();
    }
    Employer(User usr, String type,String certificat){
        this.id = usr.getId();
        this.nom = usr.getNom();
        this.prenom = usr.getPrenom();
        this.isArtiste = usr.getIsArtiste();
        this.isEmployer = usr.getIsEmployer();
        this.type = type;
        this.certificat = certificat;
    }
    public void setType(String type) {
        this.type = type;
    }

    public void setCertificat(String certificat) {
        this.certificat = certificat;
    }

    public String getType() {
        return type;
    }

    public String getCertificat() {
        return certificat;
    }
}
