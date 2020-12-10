package com.games.mastergames.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.games.mastergames.model.Category;

import java.util.List;

public class CategoryViewModel extends ViewModel {
    private MutableLiveData<List<Category>> categoryList = new MutableLiveData<>();

    public MutableLiveData<List<Category>> getCategories() {
        return categoryList;
    }

    public void setCategories(List categories) {
        this.categoryList.setValue(categories);
    }
}
