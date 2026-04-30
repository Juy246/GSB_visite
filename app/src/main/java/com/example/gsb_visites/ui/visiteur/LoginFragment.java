package com.example.gsb_visites.ui.visiteur;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gsb_visites.R;
import com.example.gsb_visites.databinding.FragmentLoginBinding;
import com.example.gsb_visites.viewmodel.VisiteurViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private VisiteurViewModel visiteurViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        visiteurViewModel = new ViewModelProvider(requireActivity()).get(VisiteurViewModel.class);

        binding.btnLogin.setOnClickListener(v -> performLogin());
    }

    private void performLogin() {
        CharSequence emailText = binding.etEmail.getText();
        CharSequence passwordText = binding.etPassword.getText();
        
        String email = emailText != null ? emailText.toString().trim() : "";
        String password = passwordText != null ? passwordText.toString().trim() : "";

        if (email.isEmpty()) {
            binding.tilEmail.setError(getString(R.string.error_email_required));
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.setError(getString(R.string.error_email_invalid));
            return;
        }
        if (password.isEmpty()) {
            binding.tilPassword.setError(getString(R.string.error_password_required));
            return;
        }
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.btnLogin.setEnabled(false);

        visiteurViewModel.login(email, password).observe(getViewLifecycleOwner(), success -> {
            binding.progressBar.setVisibility(View.GONE);
            binding.btnLogin.setEnabled(true);

            if (success) {
                visiteurViewModel.getVisiteur().observe(getViewLifecycleOwner(), visiteur -> {
                    if (visiteur != null && visiteur.getToken() != null) {
                        Toast.makeText(getContext(),
                            "Connecté ! Bienvenue " + visiteur.getEmail(),
                            Toast.LENGTH_SHORT).show();

                        NavDirections action = LoginFragmentDirections.actionLoginFragmentToHomeVisiteurFragment();
                        NavHostFragment.findNavController(LoginFragment.this).navigate(action);
                    }
                });
            } else {
                Toast.makeText(getContext(),
                    getString(R.string.error_login_failed),
                    Toast.LENGTH_SHORT).show();
                binding.tilPassword.setError(getString(R.string.error_invalid_credentials));
            }
        });
    }
}