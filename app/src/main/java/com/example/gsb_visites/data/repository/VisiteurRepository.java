package com.example.gsb_visites.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gsb_visites.data.model.ApiResponse;
import com.example.gsb_visites.data.model.Portefeuille;
import com.example.gsb_visites.data.model.Visiteur;
import com.example.gsb_visites.data.network.GsbApi;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class VisiteurRepository {
    private final MutableLiveData<Visiteur> visiteurLiveData = new MutableLiveData<>(null);
    private final GsbApi gsbApi;

    @Inject
    public VisiteurRepository(GsbApi gsbApi) {
        this.gsbApi = gsbApi;
    }

    public LiveData<Visiteur> getVisiteur() {
        return visiteurLiveData;
    }

    /**
     * Récupère les infos du visiteur (nom, prenom, email)
     * @param email email du visiteur
     * @param password mot de passe du visiteur
     * @return LiveData<Visiteur> contenant les infos du visiteur
     */
    public LiveData<Visiteur> fetchVisiteurInfo(String email, String password) {
        MutableLiveData<Visiteur> result = new MutableLiveData<>();

        Call<Visiteur> call = gsbApi.getVisiteur(email, password);
        call.enqueue(new Callback<Visiteur>() {
            @Override
            public void onResponse(@NonNull Call<Visiteur> call, @NonNull Response<Visiteur> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Visiteur visiteur = response.body();
                    visiteurLiveData.setValue(visiteur);
                    result.setValue(visiteur);
                } else {
                    result.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Visiteur> call, @NonNull Throwable t) {
                result.setValue(null);
            }
        });
        return result;
    }

    /**
     * Connecte un visiteur avec email et password
     * @param email email du visiteur
     * @param password mot de passe du visiteur
     * @return LiveData<Boolean> true si succès, false sinon
     */
    public LiveData<Boolean> login(String email, String password) {
        MutableLiveData<Boolean> success = new MutableLiveData<>();
        Visiteur loginRequest = new Visiteur(email, password);

        Call<ApiResponse> call = gsbApi.login(loginRequest);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    
                    if (apiResponse.isSuccess() && apiResponse.getVisiteur() != null) {
                        Visiteur visiteur = apiResponse.getVisiteur();
                        // Stocker le token depuis la réponse API
                        visiteur.setToken(apiResponse.getToken());
                        visiteurLiveData.setValue(visiteur);
                        success.setValue(true);
                    } else {
                        success.setValue(false);
                    }
                } else {
                    success.setValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                success.setValue(false);
            }
        });
        return success;
    }

    /**
     * Récupère les portefeuilles actifs d'un visiteur
     * @param visiteurId ID du visiteur
     * @return LiveData<List<Portefeuille>> contenant les portefeuilles actifs
     */
    public LiveData<List<Portefeuille>> getActivePortefeuilleByVisiteur(String visiteurId) {
        MutableLiveData<List<Portefeuille>> result = new MutableLiveData<>();

        Call<List<Portefeuille>> call = gsbApi.getActivePortefeuilleByVisiteur(visiteurId);
        call.enqueue(new Callback<List<Portefeuille>>() {
            @Override
            public void onResponse(@NonNull Call<List<Portefeuille>> call, @NonNull Response<List<Portefeuille>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(response.body());
                } else {
                    result.setValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Portefeuille>> call, @NonNull Throwable t) {
                result.setValue(new ArrayList<>());
            }
        });
        return result;
    }

    public void logout() {
        visiteurLiveData.setValue(null);
    }
}

