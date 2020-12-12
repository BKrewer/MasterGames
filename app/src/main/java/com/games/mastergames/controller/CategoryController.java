package com.games.mastergames.controller;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.games.mastergames.model.Category;
import com.games.mastergames.util.AppContextGame;
import com.games.mastergames.viewModels.CategoryViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryController {

    private CategoryViewModel categoryViewModel;

    public CategoryController(CategoryViewModel categoryViewModel) {
        this.categoryViewModel = categoryViewModel;
    }

    public void getCategories() {
        AppContextGame.requestQueue.start();
        String url = AppContextGame.URLAPI + "genres";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Category> categoryList = new ArrayList<>();
                        try {
                            JSONArray genres = response.getJSONArray("results");
                            for (int i = 0; i < genres.length(); i++) {
                                categoryList.add(new Category(genres.getJSONObject(i).getInt("id"), genres.getJSONObject(i).getString("name"), genres.getJSONObject(i).getString("image_background")));
                            }
                        } catch (JSONException ex) {
                        }
                        categoryViewModel.setCategories(categoryList);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
            AppContextGame.requestQueue.add(jsonObjectRequest);
    }

    public void getCategoryDetail(int id) {
        AppContextGame.requestQueue.start();
        String url = AppContextGame.URLAPI + "genres=" + id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray genres = response.getJSONArray("results");
                            for (int i = 0; i < genres.length(); i++) {
                                //category.setCategory(genres.getJSONObject(i).getInt("id"), genres.getJSONObject(i).getString("name"), genres.getJSONObject(i).getString("image_background"));
                            }
                        } catch (JSONException ex) {
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        AppContextGame.requestQueue.add(jsonObjectRequest);
    }
}