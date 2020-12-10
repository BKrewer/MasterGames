package com.games.mastergames.controller.interfaces;

import com.games.mastergames.viewModels.GameViewModel;

public interface IController  {
    void getGameList();
    void getGameById(String id);
    void getGamesByCategory(String categoryName, GameViewModel gameViewModel);
}
