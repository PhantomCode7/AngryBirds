package com.project.States;

import com.badlogic.gdx.graphics.Texture;

public class BlackBird  extends Birds {


    static Texture image = new Texture("bomb.png") ;
    BlackBird(int speed) {
        super(speed);
    }

    void blast ()
    {

    }
}
