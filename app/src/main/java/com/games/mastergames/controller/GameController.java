package com.games.mastergames.controller;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.games.mastergames.controller.interfaces.IController;
import com.games.mastergames.model.Category;
import com.games.mastergames.model.Game;
import com.games.mastergames.util.AppContextGame;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
                        Game game = Game.getInstance();
                        try {
                            JSONObject games = response.getJSONObject("results");
                            game.setGame(games.getInt("id"), games.getString("name"), games.getString("description"), games.getString("background_image"));
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
    public void getGamesByCategory(String categoryName) {
        AppContextGame.requestQueue.start();
        String url = AppContextGame.URLAPI + "games?genres=" + categoryName;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Game game = Game.getInstance();
                        try {
                            JSONArray games = response.getJSONArray("results");
                            for (int i = 0; i < games.length(); i++) {
                                game.setGame(games.getJSONObject(i).getInt("id"), games.getJSONObject(i).getString("name"), games.getJSONObject(i).getString("description"), games.getJSONObject(i).getString("background_image"));
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
