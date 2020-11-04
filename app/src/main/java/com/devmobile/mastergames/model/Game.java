package com.devmobile.mastergames.model;

public class Game {

    private int id;
    private String name;
    private String description;
    public final String imageBackground;


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

    public String getDescription() {return description;}

    public String getImagePath() {return imageBackground;}
}
