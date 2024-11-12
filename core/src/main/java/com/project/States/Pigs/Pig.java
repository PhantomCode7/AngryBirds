package com.project.States.Pigs;

import com.badlogic.gdx.graphics.Texture;

public class Pig {

    private int width ;
    private int height ;
    static Texture image = new Texture("Piggy_medium.png")  ;


    Pig ()
    {

    }
    Pig (int width , int size)
    {
        this.setWidth(width);
        this.height  = size ;
    }

    void getHit ()
    {

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
