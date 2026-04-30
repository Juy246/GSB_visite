package com.example.gsb_visites.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.gsb_visites.data.model.Portefeuille;
import com.example.gsb_visites.data.model.Visiteur;
import com.example.gsb_visites.data.repository.VisiteurRepository;

import javax.inject.Inject;
import java.util.List;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class VisiteurViewModel extends ViewModel {
    private final VisiteurRepository visiteurRepository;

    @Inject
    public VisiteurViewModel(VisiteurRepository visiteurRepository) {
        this.visiteurRepository = visiteurRepository;
    }

    public LiveData<Boolean> login(String email, String password) {
        return visiteurRepository.login(email, password);
    }

    public LiveData<Visiteur> getVisiteur() {
        return visiteurRepository.getVisiteur();
    }

    public LiveData<Visiteur> fetchVisiteurInfo(String email, String password) {
        return visiteurRepository.fetchVisiteurInfo(email, password);
    }

    public LiveData<List<Portefeuille>> getActivePortefeuilleByVisiteur(String visiteurId) {
        return visiteurRepository.getActivePortefeuilleByVisiteur(visiteurId);
    }

    public void logout() {
        visiteurRepository.logout();
    }
}


