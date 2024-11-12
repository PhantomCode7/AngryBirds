package com.project.States.Materials;

import com.badlogic.gdx.graphics.Texture;

public class Wooden {

    private static Texture horizontal  = new Texture("horizontal.png");
    private static Texture vertical  = new Texture("vertical.png");

    private float horizontalWidth ;
    private float horizontalHeight ;
    private float verticalWidth ;
    private float verticalHeight ;

    public Wooden()
    {
        horizontalWidth = 100 ;
        horizontalHeight = 15 ;
        verticalHeight = 100 ;
        verticalWidth = 15 ;
    }

    void getDamage()
    {

    }

    public float getHorizontalWidth() {
        return horizontalWidth;
    }

    public void setHorizontalWidth(float horizontalWidth) {
        this.horizontalWidth = horizontalWidth;
    }

    public float getHorizontalHeight() {
        return horizontalHeight;
    }

    public void setHorizontalHeight(float horizontalHeight) {
        this.horizontalHeight = horizontalHeight;
    }

    public float getVerticalHeight() {
        return verticalHeight;
    }

    public void setVerticalHeight(float verticalHeight) {
        this.verticalHeight = verticalHeight;
    }

    public float getVerticalWidth() {
        return verticalWidth;
    }

    public void setVerticalWidth(float verticalWidth) {
        this.verticalWidth = verticalWidth;
    }

    public static Texture getHorizontal() {
        return horizontal;
    }

    public static void setHorizontal(Texture horizontal) {
        Wooden.horizontal = horizontal;
    }

    public static Texture getVertical() {
        return vertical;
    }

    public static void setVertical(Texture vertical) {
        Wooden.vertical = vertical;
    }
}
