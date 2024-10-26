package com.project.States;

import com.badlogic.gdx.graphics.Texture;

public class Unknown {

    static Texture horizontal = new Texture("unknownHorizontal.png");
    static Texture vertical = new Texture("unknownVertical.png");

    float horizontalWidth, horizontalHeight;
    float verticalWidth, verticalHeight;
    Unknown() {
        horizontalWidth = 100 ;
        horizontalHeight = 15 ;
        verticalWidth = 15 ;
        verticalHeight = 100 ;
    }

    void getDamage()
    {

    }
}
