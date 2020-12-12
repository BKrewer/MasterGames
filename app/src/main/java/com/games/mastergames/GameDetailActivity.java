package com.games.mastergames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.games.mastergames.adapters.GamesAdapter;
import com.games.mastergames.controller.GameController;
import com.games.mastergames.model.Game;
import com.games.mastergames.viewModels.GameViewModel;

import java.util.List;


public class GameDetailActivity extends AppCompatActivity {
    TextView gameName;
    TextView gameDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        GameViewModel gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        Bundle bundle = getIntent().getExtras();
        GameController gameController = new GameController(gameViewModel);
        gameController.getGameById(bundle.getString("GAME_ID"));


        gameName = findViewById(R.id.gameName);
        gameDescription = findViewById(R.id.gameDescription);

        gameViewModel.getGameDetail().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(List<Game> games) {
                gameName.setText(games.get(0).getName());
                gameDescription.setText(games.get(0).getDescription());
            }
        });
    }
}