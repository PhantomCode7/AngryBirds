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
    private Texture login ;
    private Texture signup  ;
    Sprite image ;
    Sprite loginButton ;
    Sprite signUpButton ;
    public LoginScreen(StateManager manager) {
        super(manager);
        img = new Texture("wallpaper.png")  ;
        login = new Texture ("login.png") ;
        signup = new Texture("signup.png") ;
        image = new Sprite(img) ;
        loginButton = new Sprite(login) ;
        signUpButton = new Sprite (signup) ;

        image.setSize(800 , 500) ;
        loginButton.setSize(100 ,75);
        signUpButton.setSize(100,50) ;

        image.setPosition(0,0);
        signUpButton.setPosition(350 ,  150) ;
        loginButton.setPosition(350 , 300 );

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


           if (loginButton.getBoundingRectangle().contains(touch.x, touch.y) || signUpButton.getBoundingRectangle().contains(touch.x,touch.y))
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
        loginButton.draw(batch) ;
        signUpButton.draw(batch) ;
        batch.end();

    }

    @Override
    public void dispose() {
        img.dispose();
        login.dispose() ;
        signup.dispose() ;
    }
}
