package com.project.States.Birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Maltida extends Birds {

    private static Texture image = new Texture  ("matilda_egg.png") ;

    public Maltida(Vector2 initialPosition) {
        super("matilda_egg.png", 50, 50, initialPosition);
    }


    void dropEgg()
    {

    }

    public static Texture getImage() {
        return image;
    }

    public static void setImage(Texture image) {
        Maltida.image = image;
    }
}
