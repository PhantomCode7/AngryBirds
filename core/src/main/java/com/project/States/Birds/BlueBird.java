package com.project.States.Birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class BlueBird extends Birds {

    private static Texture image = new Texture  ("blue_bird.png") ;

    public BlueBird(Vector2 initialPosition) {
        super("blue_bird.png", 50, 50, initialPosition);
    }


    void split ()
    {

    }

    public static Texture getImage() {
        return image;
    }

    public static void setImage(Texture image) {
        BlueBird.image = image;
    }
}
