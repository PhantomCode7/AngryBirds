package com.project.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MainScreen extends State
{
    Texture mainScreen ;
    Sprite main ;
    Texture newGame ;
    Sprite newGameButton ;
    Texture loadGame ;
    Sprite loadGameButton ;
    Texture exit ;
    Sprite exitGameButton ;
    public MainScreen(StateManager manager) {
        super(manager);
        mainScreen = new Texture("MainScreen.png") ;
        newGame= new Texture("newGame.png") ;
        loadGame = new Texture("LoadGame.png") ;
        exit = new Texture("EXIT.png") ;
        main  =  new Sprite(mainScreen) ;
        newGameButton = new Sprite (newGame) ;
        loadGameButton = new Sprite (loadGame) ;
        exitGameButton = new Sprite(exit) ;
        main.setSize(800,500) ;
        main.setPosition(0,0);
        newGameButton.setSize(100 , 50) ;
        newGameButton.setPosition(350 , 325) ;
        loadGameButton.setSize(100,50);
        loadGameButton.setPosition( 350, 225);
        exitGameButton.setSize(100,50);
        exitGameButton.setPosition(350, 125) ;


    }

    @Override
    public void input() {
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            Vector2 touch = new Vector2();
            touch.set(x, y);
            Main.viewport.unproject(touch);

            if (loadGameButton.getBoundingRectangle().contains(touch.x,touch.y))
            {
                manager.set(new LevelScreen(manager)) ;
            }
            else if (exitGameButton.getBoundingRectangle().contains(touch.x , touch.y)) {
                Gdx.app.exit();
            }

            else if (newGameButton.getBoundingRectangle().contains(touch.x , touch.y))
            {
                manager.set(new GameScreen(manager)) ;
            }

        }
    }

    @Override
    public void update(float delta)
    {
        input() ;
    }

    @Override
    public void render(SpriteBatch batch)
    {
        batch.begin() ;
        main.draw(batch) ;
        newGameButton.draw(batch) ;
        loadGameButton.draw(batch) ;
        exitGameButton.draw (batch) ;
        batch.end() ;
    }

    @Override
    public void dispose() {
        mainScreen.dispose() ;
        newGame.dispose() ;
        loadGame.dispose() ;
        exit.dispose() ;
    }


}
