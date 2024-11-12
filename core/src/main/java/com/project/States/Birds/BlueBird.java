package com.project.States.Birds;

import com.badlogic.gdx.graphics.Texture;

public class BlueBird extends Birds {



    private static Texture image = new Texture  ("blue_bird.png") ;

    public BlueBird ()
    {
        super() ;
        setHeight(30);
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
