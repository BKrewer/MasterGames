package com.games.mastergames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.games.mastergames.adapters.CategoriesAdapter;
import com.games.mastergames.adapters.GamesAdapter;
import com.games.mastergames.controller.CategoryController;
import com.games.mastergames.controller.GameController;
import com.games.mastergames.model.Category;
import com.games.mastergames.model.Game;
import com.games.mastergames.viewModels.CategoryViewModel;
import com.games.mastergames.viewModels.GameViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    TextView userNameField;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    GoogleSignInClient mGoogleSignInClient;
    private Category mCategory;
    private Game mGame;
    private CategoriesAdapter adapter;
    private GamesAdapter adapterGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CategoryViewModel categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        GameViewModel gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CategoryController categoryController = new CategoryController();
        categoryController.getCategories(categoryViewModel);
        GameController gameController = new GameController();
        gameController.getGamesByCategory("action", gameViewModel);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,  R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if(acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            //userNameField = findViewById(R.id.userName);
            //userNameField.setText(personName);
            //userImage = findViewById(R.id.userImage);
            //userName.setText(personName);
            //Glide.with(this).load(String.valueOf(personPhoto)).into(userImage);
        }

        RecyclerView recycleCategories = findViewById(R.id.recyclerCategories);
        recycleCategories.setHasFixedSize(true);
        recycleCategories.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerGames = findViewById(R.id.reclyclerGamesDestaques);
        recyclerGames.setHasFixedSize(true);
        recyclerGames.setLayoutManager(new LinearLayoutManager(this));

        List<Category> categoryList = new ArrayList<>();
        adapter = new CategoriesAdapter(categoryList, categoryViewModel);
        categoryViewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                adapter.setCategoryList(categories);
                recycleCategories.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        List<Game> gameList = new ArrayList<>();
        adapterGames = new GamesAdapter(gameList, gameViewModel);
        gameViewModel.getGames().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(List<Game> games) {
                adapterGames.setGameList(games);
                recyclerGames.setAdapter(adapterGames);
                adapterGames.notifyDataSetChanged();
            }
        });
    }

    public void CategoryDetail(View view) {
        String value =  String.valueOf(view.getTag());
        Intent it = new Intent(HomeActivity.this, GamesListActivity.class);
        it.putExtra("CATEGORY_ID", value);
        startActivity(it);
    }

    public void GameDetail(View view) {
        String value =  String.valueOf(view.getTag());
        Intent it = new Intent(HomeActivity.this, GameDetailActivity.class);
        it.putExtra("GAME_ID", value);
        startActivity(it);
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