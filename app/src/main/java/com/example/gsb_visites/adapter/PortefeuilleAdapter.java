package com.example.gsb_visites.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsb_visites.data.model.Portefeuille;
import com.example.gsb_visites.databinding.ItemPortefeuilleBinding;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PortefeuilleAdapter extends RecyclerView.Adapter<PortefeuilleAdapter.PortefeuilleViewHolder> {

    private List<Portefeuille> portefeuilles;
    private SimpleDateFormat dateFormat;

    public PortefeuilleAdapter(List<Portefeuille> portefeuilles) {
        this.portefeuilles = portefeuilles;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
    }

    @NonNull
    @Override
    public PortefeuilleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPortefeuilleBinding binding = ItemPortefeuilleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PortefeuilleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PortefeuilleViewHolder holder, int position) {
        Portefeuille portefeuille = portefeuilles.get(position);
        holder.bind(portefeuille);
    }

    @Override
    public int getItemCount() {
        return portefeuilles != null ? portefeuilles.size() : 0;
    }

    public void updateData(List<Portefeuille> newPortefeuilles) {
        this.portefeuilles = newPortefeuilles;
        notifyDataSetChanged();
    }

    class PortefeuilleViewHolder extends RecyclerView.ViewHolder {
        private final ItemPortefeuilleBinding binding;

        public PortefeuilleViewHolder(@NonNull ItemPortefeuilleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Portefeuille portefeuille) {
            if (binding != null) {
                // Nom du praticien
                String praticienName = portefeuille.getPraticienName() != null ? portefeuille.getPraticienName() : "Praticien inconnu";
                binding.tvPraticienName.setText("Praticien : " + praticienName);

                // Date de début de suivi
                if (portefeuille.getDateDebutSuivi() != null) {
                    String dateDebut = dateFormat.format(portefeuille.getDateDebutSuivi());
                    binding.tvDateDebut.setText("Suivi depuis : " + dateDebut);
                }

                // Statut actif
                if (portefeuille.getDateFinSuivi() == null) {
                    binding.tvStatut.setText("Statut : Actif");
                    binding.tvStatut.setTextColor(itemView.getContext().getResources().getColor(android.R.color.holo_green_dark));
                } else {
                    String dateFin = dateFormat.format(portefeuille.getDateFinSuivi());
                    binding.tvStatut.setText("Inactif depuis : " + dateFin);
                    binding.tvStatut.setTextColor(itemView.getContext().getResources().getColor(android.R.color.holo_red_dark));
                }
            }
        }
    }
}

