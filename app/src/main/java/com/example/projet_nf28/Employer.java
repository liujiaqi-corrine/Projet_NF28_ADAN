package com.example.projet_nf28;

public class Employer extends User{
    String type;
    String certificat;

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
