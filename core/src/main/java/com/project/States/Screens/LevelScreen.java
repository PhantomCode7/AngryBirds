package com.project.States.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import  com.project.States.Main;

import com.project.States.StateManager;
import com.project.States.State ;

public class LevelScreen extends State {
    Texture back ;
    Texture level ;
    Texture level1 ;
    Texture level2 ;
    Texture level3 ;
    Sprite  backButton ;

    Sprite levelButton ;
    Sprite level1Button ;
    Sprite level2Button ;
    Sprite level3Button ;


    public LevelScreen(StateManager manager) {
        super(manager);

        level = new Texture("levelBackground1.png");
        level1 = new Texture("level1.png");
        level2 = new Texture("level2.png");
        level3 = new Texture("level3.png");

        back = new Texture("back.png");

        levelButton = new Sprite (level) ;
        levelButton = new Sprite(level);
        level1Button = new Sprite(level1);
        level2Button = new Sprite(level2);
        level3Button = new Sprite(level3);


        levelButton.setSize(800,500);
        levelButton.setPosition(0,0) ;

        level1Button.setSize(100 , 100) ;
        level2Button.setSize(100, 100);
        level3Button.setSize(100, 100);


        level1Button.setPosition(100,250) ;
        level2Button.setPosition(300,250) ;
        level3Button.setPosition(500,250) ;
        backButton = new Sprite(back) ;
        backButton.setPosition(25 , 450) ;
        backButton.setSize (50 ,50) ;




    }

    @Override
    public void input() {
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            Vector2 touch = new Vector2();
            touch.set(x, y);
            Main.viewport.unproject(touch);
            if (backButton.getBoundingRectangle().contains(touch.x , touch.y))
            {
                manager.set(new MainScreen (manager)) ;
            }
            else if (level2Button.getBoundingRectangle().contains(touch.x , touch.y)) {

                    manager.set(new Level2(manager)) ;
            }

            else if (level3Button.getBoundingRectangle().contains(touch.x , touch.y)) {

                    manager.set(new Level3(manager));
            }
            else if (level1Button.getBoundingRectangle().contains(touch.x , touch.y)) {
                manager.set(new GameScreen(manager)) ;
            }



        }
    }

    @Override
    public void update(float delta) {
        input() ;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin() ;
        levelButton.draw(batch) ;
        level1Button.draw(batch) ;
        level2Button.draw(batch) ;
        level3Button.draw(batch) ;
        backButton.draw(batch) ;
        batch.end() ;

    }

    @Override
    public void dispose() {
        if (level != null) level.dispose();
        if (level1 != null) level1.dispose();
        if (level2 != null) level2.dispose();
        if (level3 != null) level3.dispose();
        if (back != null) back.dispose();
    }
}
