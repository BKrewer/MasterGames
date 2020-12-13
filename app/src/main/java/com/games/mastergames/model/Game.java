package com.games.mastergames.model;

public class Game {
    private int id;
    private String name;
    private String description;
    private String imageBackground;
    private String developer;

    public Game(int id, String name, String imageBackground) {
        this.id = id;
        this.name = name;
        this.imageBackground = imageBackground;
    }

    public Game(int id, String name, String description, String imageBackground, String developer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageBackground = imageBackground;
        this.developer = developer;
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

    public String getDeveloper() {return developer;}
}
