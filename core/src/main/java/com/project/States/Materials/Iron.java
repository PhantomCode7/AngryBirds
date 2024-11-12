package com.project.States.Materials;

import com.badlogic.gdx.graphics.Texture;

public class Iron {

    private static Texture horizontal = new Texture("ironHorizontal.png");
    private static Texture vertical = new Texture("ironVertical.png");

    float horizontalWidth, horizontalHeight;
    float verticalWidth, verticalHeight;

    public Iron()
    {
        horizontalWidth = 100 ;
        horizontalHeight = 15 ;
        verticalHeight = 100 ;
        verticalWidth = 15 ;
    }

    void getDamage()
    {

    }

    public static Texture getHorizontal() {
        return horizontal;
    }

    public static Texture getVertical() {
        return vertical;
    }

    public static void setHorizontal(Texture horizontal) {
        Iron.horizontal = horizontal;
    }

    public static void setVertical(Texture vertical) {
        Iron.vertical = vertical;
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
}
