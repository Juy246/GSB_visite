package com.example.gsb_visites.ui.visiteur;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gsb_visites.R;
import com.example.gsb_visites.adapter.PortefeuilleAdapter;
import com.example.gsb_visites.databinding.FragmentHomeVisiteurBinding;
import com.example.gsb_visites.viewmodel.VisiteurViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeVisiteurFragment extends Fragment {

    private FragmentHomeVisiteurBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeVisiteurBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialiser le RecyclerView
        PortefeuilleAdapter portefeuilleAdapter = new PortefeuilleAdapter(new ArrayList<>());
        binding.recyclerViewPraticien.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewPraticien.setAdapter(portefeuilleAdapter);

        VisiteurViewModel visiteurViewModel = new ViewModelProvider(requireActivity()).get(VisiteurViewModel.class);
        visiteurViewModel.getVisiteur().observe(getViewLifecycleOwner(), visiteur -> {
            if (visiteur != null) {
                String nom = visiteur.getNom() != null ? visiteur.getNom() : "";
                String prenom = visiteur.getPrenom() != null ? visiteur.getPrenom() : "";
                String email = visiteur.getEmail() != null ? visiteur.getEmail() : "";
                String message = "Bonjour" + " " + prenom + " " + nom;
                binding.tvBonjour.setText(message);
                String emailMessage = "Votre adresse e-mail : " + email;
                binding.tvEmail.setText(emailMessage);

                // Charger les portefeuilles actifs si l'ID existe
                if (visiteur.getId() != null) {
                    visiteurViewModel.getActivePortefeuilleByVisiteur(visiteur.getId())
                        .observe(getViewLifecycleOwner(), portefeuilles -> {
                            if (portefeuilles != null && !portefeuilles.isEmpty()) {
                                portefeuilleAdapter.updateData(portefeuilles);
                            }
                        });
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}