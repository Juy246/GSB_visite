package com.example.gsb_visites.data.repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gsb_visites.data.model.Visiteur;
import com.example.gsb_visites.data.network.GsbApi;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;

@Singleton
public class VisiteurRepository {
    private final MutableLiveData<Visiteur> visiteurLiveData = new MutableLiveData<>(null);
    private final GsbApi apiCall;

    @Inject
    public VisiteurRepository() { }

    public LiveData<Visiteur> getVisiteur() {
        return visiteurLiveData;
    }

    public LiveData<Boolean> login(String email, String password) {
        MutableLiveData<Boolean> success = new MutableLiveData<>();

        Call<Visiteur> call = getVisiteur(email, password);
        call.enqueue(new Callback<Visiteur>() {
            @Override
            public void onResponse(Call<Visiteur> call, retrofit2.Response<Visiteur> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Visiteur visiteur = response.body();
                    visiteur.setToken(visiteur.getToken());
                    visiteurLiveData.setValue(visiteur);
                    visiteurLiveData.setValue(true);
                } else {
                    visiteurLiveData.setValue(false);
                }
            }
            @Override
            public void onFailure(Call<Visiteur> call, Throwable t) {
                visiteurLiveData.setValue(false);
            }
        });
        return success;
    }

    public void logout() {
        visiteurLiveData.setValue(null);
    }
}

