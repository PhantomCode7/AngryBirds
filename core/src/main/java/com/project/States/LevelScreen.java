package com.project.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class LevelScreen extends State{
    Texture back ;
    Texture level ;
    Texture level1 ;
    Texture level2 ;
    Texture level3 ;
    Texture level4 ;
    Texture level5 ;
    Texture level6 ;
    Texture level7 ;
    Texture level8 ;
    Sprite  backButton ;

    Sprite levelButton ;
    Sprite level1Button ;
    Sprite level2Button ;
    Sprite level3Button ;
    Sprite level4Button ;
    Sprite level5Button ;
    Sprite level6Button ;
    Sprite level7Button ;
    Sprite level8Button ;

    public LevelScreen(StateManager manager) {
        super(manager);
        level = new Texture("levelBackground1.png");
        level1 = new Texture("level1.png");
        level2 = new Texture("level2.png");
        level3 = new Texture("level3.png");
        level4 = new Texture("level4.png");
        level5 = new Texture("level5.png");
        level6 = new Texture("level6.png");
        level7 = new Texture("level7.png");
        level8 = new Texture("level8.png");
        back = new Texture ("backk.png") ;

        levelButton = new Sprite (level) ;
        levelButton = new Sprite(level);
        level1Button = new Sprite(level1);
        level2Button = new Sprite(level2);
        level3Button = new Sprite(level3);
        level4Button = new Sprite(level4);
        level5Button = new Sprite(level5);
        level6Button = new Sprite(level6);
        level7Button = new Sprite(level7);
        level8Button = new Sprite(level8);

        levelButton.setSize(800,500);
        levelButton.setPosition(0,0) ;

        level1Button.setSize(50 , 50) ;
        level2Button.setSize(50, 50);
        level3Button.setSize(50, 50);
        level4Button.setSize(50, 50);
        level5Button.setSize(50, 50);
        level6Button.setSize(50, 50);
        level7Button.setSize(50, 50);
        level8Button.setSize(50, 50);

        level1Button.setPosition(100,300) ;
        level2Button.setPosition(250,300) ;
        level3Button.setPosition(400,300) ;
        level4Button.setPosition(550,300) ;
        level5Button.setPosition( 100, 150 ) ;
        level6Button.setPosition( 250 , 150 ) ;
        level7Button.setPosition(400 , 150 ) ;
        level8Button.setPosition(550 , 150) ;
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
                manager.set(new GameScreen(manager)) ;
            }

            else if (level3Button.getBoundingRectangle().contains(touch.x , touch.y)) {
                manager.set(new GameScreen(manager)) ;
            }
            else if (level4Button.getBoundingRectangle().contains(touch.x , touch.y)) {
                manager.set(new GameScreen(manager)) ;
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
        level4Button.draw(batch) ;
        level5Button.draw(batch) ;
        level6Button.draw(batch) ;
        level7Button.draw(batch) ;
        level8Button.draw(batch) ;
        backButton.draw(batch) ;
        batch.end() ;

    }

    @Override
    public void dispose() {

    }
}
