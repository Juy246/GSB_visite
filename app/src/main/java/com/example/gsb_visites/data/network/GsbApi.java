package com.example.gsb_visites.data.network;


import com.example.gsb_visites.data.model.Visiteur;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GsbApi {
    @POST("connexion")
    Call<Visiteur> login(@Body Visiteur visiteur);
        @GET("visiteur")
        Call<Visiteur> getVisiteur(@Query("email") String email, @Query("mdp") String mdp);
}