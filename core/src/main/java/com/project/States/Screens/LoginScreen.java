package com.project.States.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.project.States.Main;
import com.project.States.State;
import com.project.States.StateManager;

public class LoginScreen extends State {

    private Texture img ;
    private Texture play ;
    Sprite image ;
    Sprite playButton ;
    public LoginScreen(StateManager manager) {
        super(manager);
        img = new Texture("wallpaper.png")  ;
        play = new Texture ("angryPlay.png") ;
        image = new Sprite(img) ;
        playButton = new Sprite(play) ;


        image.setSize(800 , 500) ;
        playButton.setSize(200 ,100);


        image.setPosition(0,0);

        playButton.setPosition(300 , 250 );

    }

    @Override
    public void input() {
        if (Gdx.input.justTouched())
        {
            float x = Gdx.input.getX();
            float y =   Gdx.input.getY() ;
            Vector2 touch = new Vector2() ;
            touch.set(x,y) ;
            Main.viewport.unproject(touch);


           if (playButton.getBoundingRectangle().contains(touch.x, touch.y) )
            {
               manager.set(new MainScreen(manager)) ;
            }

        }
    }

    @Override
    public void update(float delta) {
        input() ;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin()  ;
        image.draw(batch) ;
        playButton.draw(batch) ;
        batch.end();

    }

    @Override
    public void dispose() {
        img.dispose();
        play.dispose() ;
    }
}
