package com.games.mastergames.controller.interfaces;

public interface IController  {
    void getGameList();
    void getGameById(String id);
    void getGamesByCategory(String categoryName);
}
