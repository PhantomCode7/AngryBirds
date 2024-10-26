package com.project.States;

import com.badlogic.gdx.graphics.Texture;

public class Wooden {

    static Texture horizontal  = new Texture("horizontal.png");
    static Texture vertical  = new Texture("vertical.png");

    float horizontalWidth ;
    float horizontalHeight ;
    float verticalWidth ;
    float verticalHeight ;

    Wooden()
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
