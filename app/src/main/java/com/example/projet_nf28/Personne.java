package com.example.projet_nf28;

public class Personne {
    private int id;
    private String name;
    private String[] candidate;
    private Adresse adresse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCandidate() {
        return candidate;
    }

    public void setCandidate(String[] candidate) {
        this.candidate = candidate;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse address) {
        this.adresse = address;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n id:" + this.id);
        sb.append("\n name:" + this.name);
        if (this.candidate != null) {
            sb.append("\n website: ");
            for (String website : this.candidate) {
                sb.append(website + ", ");
            }
        }
        if (this.adresse != null) {
            sb.append("\n address:" + this.adresse.toString());
        }
        return sb.toString();
    }








}
