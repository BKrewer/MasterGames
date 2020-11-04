package com.devmobile.mastergames;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.devmobile.mastergames.services.Constantes;
import com.devmobile.mastergames.services.GameFW;

import org.json.JSONArray;
import org.json.JSONObject;

public class GameDetail extends AppCompatActivity implements Response.Listener<JSONObject> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        String gameId = getIntent().getStringExtra("GAME_ID");;

        Constantes.requestQueue.start();
        String url = Constantes.URLAPI+"games/" + gameId;

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url,
                null, (Response.Listener<JSONObject>) this,null);
        Constantes.requestQueue.add(jor);

        RelativeLayout layout = findViewById(R.id.gameDetail);
        GameFW cfw = GameFW.getInstance();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray arr = new JSONArray(response);
            JSONObject gameObj = arr.getJSONObject(0);

            String name = gameObj.getString("name");
            String description = gameObj.getString("description");
            String backgroundImg = gameObj.getString("background_image");

            TextView gameName = findViewById(R.id.gameName);
            TextView gameDescription = findViewById(R.id.gameLongDescription);
            ImageView gameBG = findViewById(R.id.gameWallpaper);

            gameName.setText(name);
            gameDescription.setText(description);
            Glide.with(this).load(String.valueOf(backgroundImg)).into(gameBG);
        } catch (Exception ex) {}
    }
}