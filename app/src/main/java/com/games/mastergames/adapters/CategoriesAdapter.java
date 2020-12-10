package com.games.mastergames.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.games.mastergames.R;
import com.games.mastergames.databinding.CategoryListBinding;
import com.games.mastergames.model.Category;
import com.games.mastergames.viewModels.CategoryViewModel;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private List<Category> categoryList;
    private CategoryViewModel categoryViewModel;

    public CategoriesAdapter(List<Category> categoryList, CategoryViewModel categoryViewModel) {
        this.categoryList = categoryList;
        this.categoryViewModel = categoryViewModel;
    }

    public void setCategoryList(List<Category> categories) {
        this.categoryList = categories;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater  = LayoutInflater.from(parent.getContext());
        final CategoryListBinding binding = CategoryListBinding.inflate(layoutInflater, parent, false);
        return new CategoriesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.setBinding(category, categoryViewModel);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class CategoriesViewHolder extends RecyclerView.ViewHolder {
        CategoryListBinding binding;

        public CategoriesViewHolder(@NonNull CategoryListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void setBinding(Category category, CategoryViewModel viewModel) {
            binding.setCategory(category);
            binding.setViewModel(viewModel);
            binding.executePendingBindings();
        }
    }
}
