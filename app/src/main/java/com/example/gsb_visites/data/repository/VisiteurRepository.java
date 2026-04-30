package com.example.gsb_visites.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gsb_visites.data.model.ApiResponse;
import com.example.gsb_visites.data.model.Visiteur;
import com.example.gsb_visites.data.network.GsbApi;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void logout() {
        visiteurLiveData.setValue(null);
    }
}

