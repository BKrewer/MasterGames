package com.games.mastergames.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Game {
    private int id;
    private String name;
    private String description;
    private String imageBackground;

    public Game(int id, String name, String description, String imageBackground) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageBackground = imageBackground;
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
}
