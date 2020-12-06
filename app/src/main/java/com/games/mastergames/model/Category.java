package com.games.mastergames.model;

public class Category {
    private int id;
    private String name;
    private String imageBackground;

    public Category(int id, String name, String imageBackground) {
        this.id = id;
        this.name = name;
        this.imageBackground = imageBackground;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {return imageBackground;}
}
