package com.example.gsb_visites.data.network;

import com.example.gsb_visites.data.model.ApiResponse;
import com.example.gsb_visites.data.model.Visiteur;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GsbApi {
    /**
     * POST /api/visiteur/login - Se connecter en tant que visiteur
     * @param visiteur contenant email et password
     * @return ApiResponse avec succes, message, token et visiteur
     */
    @POST("visiteur/login")
    Call<ApiResponse> login(@Body Visiteur visiteur);

    /**
     * GET /api/visiteur - Récupérer les infos du visiteur
     */
    @GET("visiteur")
    Call<Visiteur> getVisiteur(@Query("email") String email, @Query("password") String password);
}