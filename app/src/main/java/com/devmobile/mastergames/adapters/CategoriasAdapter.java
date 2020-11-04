package com.devmobile.mastergames.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devmobile.mastergames.databinding.LayoutCategoriaBinding;
import com.devmobile.mastergames.model.Categoria;

import java.util.List;


public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.CategoriaViewHolder> {
    private List<Categoria> categorias;

    public CategoriasAdapter(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public CategoriasAdapter.CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LayoutCategoriaBinding itemBinding = LayoutCategoriaBinding.inflate(layoutInflater, parent, false);
        return new CategoriaViewHolder(itemBinding);
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        holder.bind(categorias.get(position));
    }

    public static class CategoriaViewHolder extends RecyclerView.ViewHolder {
        LayoutCategoriaBinding binding;
        public View view;
        public CategoriaViewHolder(LayoutCategoriaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Categoria cat) {
            binding.setCategoria(cat);
            binding.executePendingBindings();
        }
    }
}
