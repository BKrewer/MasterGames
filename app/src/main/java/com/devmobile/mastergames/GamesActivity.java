//package com.devmobile.mastergames;
//
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.databinding.DataBindingUtil;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.RelativeLayout;
//
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.devmobile.mastergames.adapters.CategoriasAdapter;
//import com.devmobile.mastergames.adapters.GamesAdapter;
//import com.devmobile.mastergames.model.Categoria;
//import com.devmobile.mastergames.model.Game;
//import com.devmobile.mastergames.services.CategoriasFW;
//import com.devmobile.mastergames.services.Constantes;
//import com.devmobile.mastergames.services.GameFW;
//import com.devmobile.mastergames.services.GameServices;
//import com.devmobile.mastergames.databinding.ActivityGamesBinding;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//import static com.android.volley.Response.*;
//
//public class GamesActivity extends AppCompatActivity implements Listener {
//    ActivityGamesBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = DataBindingUtil.setContentView(this,R.layout.activity_games);
//
//        String categoryId = getIntent().getStringExtra("CATEGORY_ID");;
//
//        Constantes.requestQueue.start();
//        String url = Constantes.URLAPI+"genres/" + categoryId;
//
//        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url,
//                null, (Response.Listener<JSONObject>) this,null);
//        Constantes.requestQueue.add(jor);
//
//        RelativeLayout layout = findViewById(R.id.gamesList);
//        GameFW cfw = GameFW.getInstance();
//    }
//
//    @Override
//    public void onResponse(Object response) {
//        GameFW gfw = GameFW.getInstance();
//        try {
//            JSONArray genres = response.getJSONArray("results");
//            for(int i = 0; i < genres.length(); i++) {
//                gfw.addGame(new Game(genres.getJSONObject(i).getInt("id"),genres.getJSONObject(i).getString("name"), genres.getJSONObject(i).getString("image_background")));
//            }
//        } catch (JSONException ex) {}
//        GamesAdapter adap = new GamesAdapter(new ArrayList<Game>(gfw.getGames()));
//        RecyclerView rv = findViewById(R.id.reclyclerCategorias);
//        rv.setAdapter(adap);
//    }
//}