package com.project.States.Birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class RedBird extends Birds {

    private static Texture image = new Texture  ("red_bird.png") ;

    public RedBird(Vector2 initialPosition) {
        super("red_bird.png", 50, 50, initialPosition);
    }

    public static Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }
}

