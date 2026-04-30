package com.example.gsb_visites.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Portefeuille implements Serializable {

    @SerializedName("_id")
    private String id;

    @SerializedName("dateDebutSuivi")
    private Date dateDebutSuivi;

    @SerializedName("dateFinSuivi")
    private Date dateFinSuivi;

    @SerializedName("visiteur")
    private String visiteur;

    @SerializedName("praticien")
    private String praticien;

    @SerializedName("praticienName")
    private String praticienName;

    public Portefeuille() {}

    public Portefeuille(String id, Date dateDebutSuivi, Date dateFinSuivi, String visiteur, String praticien) {
        this.id = id;
        this.dateDebutSuivi = dateDebutSuivi;
        this.dateFinSuivi = dateFinSuivi;
        this.visiteur = visiteur;
        this.praticien = praticien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateDebutSuivi() {
        return dateDebutSuivi;
    }

    public void setDateDebutSuivi(Date dateDebutSuivi) {
        this.dateDebutSuivi = dateDebutSuivi;
    }

    public Date getDateFinSuivi() {
        return dateFinSuivi;
    }

    public void setDateFinSuivi(Date dateFinSuivi) {
        this.dateFinSuivi = dateFinSuivi;
    }

    public String getVisiteur() {
        return visiteur;
    }

    public void setVisiteur(String visiteur) {
        this.visiteur = visiteur;
    }

    public String getPraticien() {
        return praticien;
    }

    public void setPraticien(String praticien) {
        this.praticien = praticien;
    }

    public String getPraticienName() {
        return praticienName;
    }

    public void setPraticienName(String praticienName) {
        this.praticienName = praticienName;
    }
}

