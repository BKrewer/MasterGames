package com.games.mastergames.controller;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.games.mastergames.controller.interfaces.IController;
import com.games.mastergames.model.Category;
import com.games.mastergames.model.Game;
import com.games.mastergames.util.AppContextGame;
import com.games.mastergames.viewModels.GameViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GameController implements IController {

    @Override
    public void getGameList() {
        AppContextGame.requestQueue.start();
        String url = AppContextGame.URLAPI + "games";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Category category = Category.getInstance();
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

    @Override
    public void getGameById(String id) {
        AppContextGame.requestQueue.start();
        String url = AppContextGame.URLAPI + "games?genres=" + id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Game> gameList = new ArrayList<>();
                        try {
                            JSONObject games = response.getJSONObject("results");
                            gameList.add(new Game(games.getInt("id"), games.getString("name"), games.getString("description"), games.getString("background_image")));
                        } catch (JSONException ex) {}
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        AppContextGame.requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void getGamesByCategory(String categoryName, GameViewModel gameViewModel) {
        AppContextGame.requestQueue.start();
        String url = AppContextGame.URLAPI + "games?genres=" + categoryName;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Game> gameList = new ArrayList<>();
                        try {
                            JSONObject games = response.getJSONObject("results");
                            gameList.add(new Game(games.getInt("id"), games.getString("name"), games.getString("description"), games.getString("background_image")));
                        } catch (JSONException ex) {
                        }
                        gameViewModel.setGames(gameList);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        AppContextGame.requestQueue.add(jsonObjectRequest);
    }
}
