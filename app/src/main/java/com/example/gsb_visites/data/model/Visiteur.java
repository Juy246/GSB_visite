package com.example.gsb_visites.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Visiteur implements Serializable {

    @SerializedName("_id")
    private String id;
    private String email;
    @SerializedName("password")
    private String password;
    private String token;
    private String nom;
    private String prenom;

    public Visiteur() {}
    public Visiteur(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
