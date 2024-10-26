package com.project.States;

import com.badlogic.gdx.graphics.Texture;

public class Iron {

    static Texture horizontal = new Texture("ironHorizontal.png");
    static Texture vertical = new Texture("ironVertical.png");

    float horizontalWidth, horizontalHeight;
    float verticalWidth, verticalHeight;

    Iron()
    {
        horizontalWidth = 100 ;
        horizontalHeight = 15 ;
        verticalHeight = 100 ;
        verticalWidth = 15 ;
    }

    void getDamage()
    {

    }
}
