package com.games.mastergames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.games.mastergames.adapters.GamesAdapter;
import com.games.mastergames.adapters.GamesListAdapter;
import com.games.mastergames.controller.GameController;
import com.games.mastergames.model.Game;
import com.games.mastergames.viewModels.GameViewModel;

import java.util.ArrayList;
import java.util.List;

public class GamesListActivity extends AppCompatActivity {

    private GamesListAdapter adapterGames;
    TextView gameName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);

        GameViewModel gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        Bundle bundle = getIntent().getExtras();

        GameController gameController = new GameController(gameViewModel);
        gameController.getGamesByCategory(bundle.getString("CATEGORY_ID"));

        List<Game> gameList = new ArrayList<>();
        adapterGames = new GamesListAdapter(getApplicationContext(), gameList, gameViewModel);

        RecyclerView recyclerGames = findViewById(R.id.reclyclerGamesList);
        recyclerGames.setHasFixedSize(true);
        recyclerGames.setLayoutManager(new LinearLayoutManager(this));

        gameViewModel.getGames().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(List<Game> games) {
                adapterGames.setGameList(games);
                recyclerGames.setAdapter(adapterGames);
                adapterGames.notifyDataSetChanged();
            }
        });
    }

    public void GameDetail(View view) {
        String value =  String.valueOf(view.getTag());
        Intent it = new Intent(GamesListActivity.this, GameDetailActivity.class);
        it.putExtra("GAME_ID", value);
        startActivity(it);
    }
}