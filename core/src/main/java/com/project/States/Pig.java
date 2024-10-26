package com.project.States;

import com.badlogic.gdx.graphics.Texture;

public class Pig {

    int width ;
    int height ;
    static Texture image = new Texture("Piggy_medium.png")  ;


    Pig ()
    {

    }
    Pig (int width , int size)
    {
        this.width = width ;
        this.height  = size ;
    }

    void getHit ()
    {

    }
}
