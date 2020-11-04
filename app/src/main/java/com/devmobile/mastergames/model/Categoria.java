package com.devmobile.mastergames.model;

public class Categoria {
    public final int id;
    public final String name;
    public final String imageBackground;

    public Categoria(int id, String name, String imageBackground) {
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