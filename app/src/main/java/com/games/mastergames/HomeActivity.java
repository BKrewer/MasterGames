package com.games.mastergames;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.games.mastergames.adapters.CategoriesAdapter;
import com.games.mastergames.adapters.GamesAdapter;
import com.games.mastergames.adapters.GamesListAdapter;
import com.games.mastergames.controller.CategoryController;
import com.games.mastergames.controller.GameController;
import com.games.mastergames.model.Category;
import com.games.mastergames.model.Game;
import com.games.mastergames.viewModels.CategoryViewModel;
import com.games.mastergames.viewModels.GameViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    TextView userNameField;
    TextView userEmail;
    ImageView userImage;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    GoogleSignInClient mGoogleSignInClient;
    private CategoriesAdapter adapter;
    private GamesListAdapter adapterGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CategoryViewModel categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        GameViewModel gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<Category> categoryList = new ArrayList<>();
        adapter = new CategoriesAdapter(categoryList, categoryViewModel);
        ArrayList<Game> listFavoritesGames = new ArrayList<>();
        adapterGames = new GamesListAdapter(getApplicationContext(), listFavoritesGames, gameViewModel);

        CategoryController categoryController = new CategoryController(categoryViewModel);
        categoryController.getCategories();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Favorites").child("games");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listFavoritesGames.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Game game = snapshot.getValue(Game.class);
                    listFavoritesGames.add(game);
                }
                gameViewModel.setFavoriteGames(listFavoritesGames);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);

        View headerLayout =
                navigationView.inflateHeaderView(R.layout.drawer_header);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,  R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        if(acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();

            userNameField = headerLayout.findViewById(R.id.userName);
            userNameField.setText(personName);
            userEmail = headerLayout.findViewById(R.id.userEmail);
            userEmail.setText(personEmail);
        }

        RecyclerView recycleCategories = findViewById(R.id.recyclerCategories);
        recycleCategories.setHasFixedSize(true);
        recycleCategories.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerGames = findViewById(R.id.reclyclerGamesDestaques);
        recyclerGames.setHasFixedSize(true);
        recyclerGames.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        categoryViewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                adapter.setCategoryList(categories);
                recycleCategories.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                TextView load = findViewById(R.id.categoryLoad);
                load.setVisibility(View.INVISIBLE);
            }
        });

        gameViewModel.getFavoriteGames().observe(this, new Observer<List<Game>>() {
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
        it.putExtra("CATEGORY_NAME", value);
        startActivity(it);
    }

    public void GameDetail(View view) {
        String value =  String.valueOf(view.getTag());
        Intent it = new Intent(HomeActivity.this, GameDetailActivity.class);
        it.putExtra("GAME_ID", value);
        it.putExtra("CATEGORY_NAME", "Action");
        startActivity(it);
    }

    public void signOut(MenuItem item) {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, task -> {
                    Intent it = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(it);
                });
    }
}