package com.project.States.Birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class BlackBird extends Birds {

    private static Texture image = new Texture  ("bomb.png") ;

    public BlackBird(Vector2 initialPosition) {
        super("bomb.png", 50, 50, initialPosition);
    }

    void blast ()
    {

    }

    public static Texture getImage() {
        return image;
    }

    public static void setImage(Texture image) {
        BlackBird.image = image;
    }
}
