package com.project.States.Birds;



import com.badlogic.gdx.graphics.Texture;

public class YellowBird extends Birds {

    private static Texture image = new Texture  ("yellow_bird.png") ;

    public YellowBird()
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
        YellowBird.image = image;
    }
}
