package com.project.States.Birds;

import com.badlogic.gdx.graphics.Texture;

public class BigRed extends Birds {



    private static Texture image = new Texture  ("big_red.png") ;

    public BigRed ()
    {

        super() ;
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
