package com.games.mastergames.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.games.mastergames.model.Game;

import java.util.List;

public class GameViewModel extends ViewModel {
    private MutableLiveData<List<Game>> gameList = new MutableLiveData<>();

    public MutableLiveData<List<Game>> getGames() {
        return gameList;
    }

    public void setGames(List games) {
        this.gameList.setValue(games);
    }
}
