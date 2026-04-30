package com.example.gsb_visites.data.model;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    private boolean success;
    private String message;
    private String token;
    
    @SerializedName("data")
    private Visiteur visiteur;
    public ApiResponse() {}

    public ApiResponse(boolean success, String message, String token, Visiteur visiteur) {
        this.success = success;
        this.message = message;
        this.token = token;
        this.visiteur = visiteur;
    }

    // Getters et Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Visiteur getVisiteur() {
        return visiteur;
    }

    public void setVisiteur(Visiteur visiteur) {
        this.visiteur = visiteur;
    }
}
