package com.games.mastergames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.games.mastergames.adapters.GamesAdapter;
import com.games.mastergames.controller.GameController;
import com.games.mastergames.model.Game;
import com.games.mastergames.viewModels.GameViewModel;

import java.util.List;


public class GameDetailActivity extends AppCompatActivity {
    TextView gameName;
    TextView gameDev;
    TextView gameDescription;
    ImageView gameBackground;

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
        gameDev = findViewById(R.id.gameDev);
        gameBackground = findViewById(R.id.gameBackground);

        gameViewModel.getGameDetail().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(List<Game> games) {
                gameName.setText(games.get(0).getName());
                gameDev.setText(games.get(0).getDeveloper());
                gameDescription.setText(android.text.Html.fromHtml(games.get(0).getDescription()).toString());
                Glide.with(getApplicationContext()).load(String.valueOf(games.get(0).getImageBackground())).into(gameBackground);
            }
        });
    }
}