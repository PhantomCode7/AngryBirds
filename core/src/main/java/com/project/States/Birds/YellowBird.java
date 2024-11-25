package com.project.States.Birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class YellowBird extends Birds {

    private static Texture image = new Texture  ("yellow_bird.png") ;

    public YellowBird(Vector2 initialPosition) {
        super("yellow_bird.png", 50, 50, initialPosition);
    }

    void increase_speed()
    {

    }

    public static Texture getImage() {
        return image;
    }

    public static void setImage(Texture image) {
        YellowBird.image = image;
    }
}
