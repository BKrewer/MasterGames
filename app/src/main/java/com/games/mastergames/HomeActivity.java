package com.games.mastergames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
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
import com.games.mastergames.controller.CategoryController;
import com.games.mastergames.model.Category;
import com.games.mastergames.model.Game;
import com.games.mastergames.viewModels.CategoryViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    TextView userNameField;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    GoogleSignInClient mGoogleSignInClient;
    private Category mCategory;
    private Game mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CategoryController categoryController = new CategoryController();
        categoryController.getCategories();

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
        recycleCategories.setLayoutManager(new LinearLayoutManager(this));

        final CategoryViewModel categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        categoryViewModel.getCategories().observe(this, categories -> {
            recycleCategories.setAdapter(new CategoriesAdapter(categories, categoryViewModel));
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