package com.devmobile.mastergames;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.devmobile.mastergames.adapters.CategoriasAdapter;
import com.devmobile.mastergames.model.Categoria;
import com.devmobile.mastergames.services.CategoriasFW;
import com.devmobile.mastergames.services.Constantes;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements Response.Listener<JSONObject> {
    DrawerLayout drawerLayout;
    ImageView userImage;
    TextView userName;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();


        Constantes.requestQueue.start();
        String url = Constantes.URLAPI+"genres";

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url,
                null, this,null);
        Constantes.requestQueue.add(jor);

        LinearLayout layout = findViewById(R.id.layoutPrincipalCategorias);
        CategoriasFW cfw = CategoriasFW.getInstance();
        RecyclerView rv = findViewById(R.id.reclyclerCategorias);
        StaggeredGridLayoutManager lmm = new StaggeredGridLayoutManager(1,LinearLayout.VERTICAL);
        rv.setLayoutManager(lmm);


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if(acct != null) {
            String personName = acct.getDisplayName();
            Uri personPhoto = acct.getPhotoUrl();

            userName = findViewById(R.id.userName);
            userImage = findViewById(R.id.userImage);
            userName.setText(personName);
            Glide.with(this).load(String.valueOf(personPhoto)).into(userImage);
        }
    }

    public void CategoryDetail(View view) {
        String value =  String.valueOf(view.getTag());
        Intent it = new Intent(HomeActivity.this, Games.class);
        it.putExtra("CATEGORY_ID", value);
        startActivity(it);
    }

    public void GameDetail(View view) {
        String value =  String.valueOf(view.getTag());
        Intent it = new Intent(HomeActivity.this, GameDetail.class);
        it.putExtra("GAME_ID", value);
        startActivity(it);
    }

    @Override
    public void onResponse(JSONObject response) {
        CategoriasFW cfw = CategoriasFW.getInstance();
        try {
            JSONArray genres = response.getJSONArray("results");
            for(int i = 0; i < genres.length(); i++) {
                cfw.addCategoria(new Categoria(genres.getJSONObject(i).getInt("id"),genres.getJSONObject(i).getString("name"), genres.getJSONObject(i).getString("image_background")));
            }
        } catch (JSONException ex) {}
        CategoriasAdapter adap = new CategoriasAdapter(new ArrayList<Categoria>(cfw.getCategorias()));
        RecyclerView rv = findViewById(R.id.reclyclerCategorias);
        rv.setAdapter(adap);
    }

    public void signOut(MenuItem item) {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent it = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(it);
                    }
                });
    }
}