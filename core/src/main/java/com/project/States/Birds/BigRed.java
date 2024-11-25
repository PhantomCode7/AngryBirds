package com.project.States.Birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class BigRed extends Birds {

    private static Texture image = new Texture  ("big_red.png") ;

    public BigRed(Vector2 initialPosition) {
        super("big_red.png", 50, 50, initialPosition);
    }

    void increase_speed()
    {

    }

    public static Texture getImage() {
        return image;
    }

    public static void setImage(Texture image) {
        BigRed.image = image;
    }
}
