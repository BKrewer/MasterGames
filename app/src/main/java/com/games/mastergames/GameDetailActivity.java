package com.games.mastergames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.games.mastergames.adapters.GamesDetailAdapter;
import com.games.mastergames.adapters.GamesListAdapter;
import com.games.mastergames.controller.GameController;
import com.games.mastergames.model.Game;
import com.games.mastergames.viewModels.GameViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GameDetailActivity extends AppCompatActivity {
    TextView gameName;
    TextView gameDev;
    TextView gameDescription;
    TextView listGamesDetail;
    ImageView gameBackground;
    private int gameId;
    private GamesDetailAdapter adapterGames;
    String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        GameViewModel gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        Bundle bundle = getIntent().getExtras();
        GameController gameController = new GameController(gameViewModel);
        gameController.getGameById(bundle.getString("GAME_ID"));
        categoryName = bundle.getString("CATEGORY_NAME");

        listGamesDetail = findViewById(R.id.listGamesDetail);
        listGamesDetail.setText(categoryName + " Games");

        gameController.getGamesByCategory(categoryName);

        List<Game> gameList = new ArrayList<>();
        adapterGames = new GamesDetailAdapter(getApplicationContext(), gameList, gameViewModel);

        gameName = findViewById(R.id.gameName);
        gameDescription = findViewById(R.id.gameDescription);
        gameDev = findViewById(R.id.gameDev);
        gameBackground = findViewById(R.id.gameBackground);

        gameViewModel.getGameDetail().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(List<Game> games) {
                gameName.setText(games.get(0).getName());
                gameDev.setText(games.get(0).getDeveloper());
                gameId = games.get(0).getId();
                gameDescription.setText(android.text.Html.fromHtml(games.get(0).getDescription()).toString());
                Glide.with(getApplicationContext()).load(String.valueOf(games.get(0).getImageBackground())).into(gameBackground);
            }
        });

        RecyclerView recyclerGames = findViewById(R.id.reclyclerGamesListDetail);
        recyclerGames.setHasFixedSize(true);
        recyclerGames.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        gameViewModel.getGames().observe(this, games -> {
            adapterGames.setGameList(games);
            recyclerGames.setAdapter(adapterGames);
            adapterGames.notifyDataSetChanged();
        });
    }

    public void GameDetail(View view) {
        String value =  String.valueOf(view.getTag());
        Intent it = new Intent(GameDetailActivity.this, GameDetailActivity.class);
        it.putExtra("GAME_ID", value);
        it.putExtra("CATEGORY_NAME", categoryName);
        startActivity(it);
    }

    public void shareGameLink(View view) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "http://app.mastergames.com/gameid=" + gameId);
        startActivity(Intent.createChooser(shareIntent, "Share link using"));
    }


    public void onShareItem(View v) {
        ImageView ivImage = findViewById(R.id.gameBackground);
        Uri bmpUri = getLocalBitmapUri(ivImage);
        if (bmpUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            shareIntent.setType("image/*");
            startActivity(Intent.createChooser(shareIntent, "Share Image"));
        } else {
        }
    }

    public Uri getLocalBitmapUri(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }

        Uri bmpUri = null;
        try {
            File file =  new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    public Uri getBitmapFromDrawable(Bitmap bmp){

        Uri bmpUri = null;
        try {
            File file =  new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();

            bmpUri = FileProvider.getUriForFile(GameDetailActivity.this, "com.games.mastergames", file);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }
}