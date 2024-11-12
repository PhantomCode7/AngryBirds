package com.project.States.Birds;

import com.badlogic.gdx.graphics.Texture;

public class RedBird extends Birds {

    private static Texture image = new Texture  ("red_bird.png") ;

    public RedBird() {

        super();
    }

    public static Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }
}

