package com.games.mastergames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.games.mastergames.controller.GameController;
import com.games.mastergames.model.Category;
import com.games.mastergames.model.Game;

import java.util.Observable;
import java.util.Observer;

public class GameDetailActivity extends AppCompatActivity implements Observer {
    private Game mGame;
    TextView gameName;
    TextView gameDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        mGame = Game.getInstance();
        mGame.addObserver(this);

        Bundle bundle = getIntent().getExtras();
        GameController gameController = new GameController();
        gameController.getGameById(bundle.getString("GAME_ID"));


        gameName = findViewById(R.id.gameName);
        gameDescription = findViewById(R.id.gameDescription);
    }

    @Override
    public void update(Observable o, Object arg) {
        gameName.setText(mGame.getName());
        gameDescription.setText(mGame.getDescription());
    }
}