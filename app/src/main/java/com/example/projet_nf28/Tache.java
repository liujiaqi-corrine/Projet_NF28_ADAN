package com.example.projet_nf28;

import android.os.Parcel;
import android.os.Parcelable;

public class Tache implements Parcelable {
    //private int mData;
    private String nom;
    private String statut;
    private String prio;
    private String deadline;

    public String getNom() {
        return nom;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getPrio() {
        return prio;
    }

    public String getStatut() {
        return statut;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrio(String prio) {
        this.prio = prio;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(statut);
        dest.writeString(prio);
        dest.writeString(deadline);
    }

    public static final Parcelable.Creator<Tache> CREATOR
            = new Parcelable.Creator<Tache>() {
        @Override
        public Tache createFromParcel(Parcel source){
            return new Tache(source);
        }
        @Override
        public Tache[] newArray(int size){
            return new Tache[size];
        }
    };

    public Tache(){

    }

    public Tache(Parcel source) {
        //mData = source.readInt();
        nom = source.readString();
        statut = source.readString();
        prio = source.readString();
        deadline = source.readString();
    }
}
