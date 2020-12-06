package com.games.mastergames.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Game extends Observable {
    private static Game instancia;
    private int id;
    private String name;
    private String description;
    private String imageBackground;
    private List<Game> gameList;

    public Game() {
        gameList = new ArrayList<>();
    }

    public Game(int id, String name, String description, String imageBackground) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageBackground = imageBackground;
    }

    public void setGame(int id, String name, String description, String imageBackground) {
        gameList.add(new Game(id, name, description, imageBackground));
        setChanged();
        notifyObservers("game");
    }

    public static Game getInstance() {
        if (instancia == null) {
            instancia = new Game();
        }
        return instancia;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageBackground() {
        return imageBackground;
    }

    public List getGameList() {return gameList;}
}
