package com.project.States.Birds;

import com.badlogic.gdx.graphics.Texture;

public class BlackBird  extends Birds {


    private static Texture image = new Texture("bomb.png") ;

    public BlackBird ()
    {
        super() ;
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
