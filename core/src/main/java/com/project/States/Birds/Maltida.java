package com.project.States.Birds;



import com.badlogic.gdx.graphics.Texture;

public class Maltida extends Birds {



    private static Texture image = new Texture  ("matilda_egg.png") ;

    public Maltida ()
    {
        super() ;
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
