package com.example.projet_nf28;

public class Adresse {

    private String street;
    private String city;

    public Adresse() {

    }

    public Adresse(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return street + ", " + city;
    }

}