package com.project.States;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;


public class GameScreen extends State{


    Texture wrong ;
    Sprite wrongButton ;
    Texture right ;
    Sprite rightButton ;
    Texture background;
    Texture sling;
    Sprite gameBackground;
    Sprite redBird;
    Sprite blueBird;
    Sprite yellowBird;
    Sprite matilda;
    Sprite blackBird;
    Sprite bigRed;
    Sprite slingShot;
    Texture pause ;
    Sprite pauseButton ;
    Texture horizontal ;
    Texture vertical ;
    Sprite horizontalButton ;
    Sprite verticalButton ;
    Texture pig ;
    Sprite piggo ;







    public GameScreen(StateManager manager) {
        super(manager);
        wrong = new Texture ("wrong.png") ;
        right = new Texture ("right.png") ;
        pig = new Texture("Piggy_medium.png") ;
        piggo = new Sprite (pig) ;
        piggo.setSize(50,50) ;
        rightButton = new Sprite(right) ;
        wrongButton = new Sprite(wrong) ;
        background = new Texture("gameBackground.png") ;
        sling = new Texture ("slingShot.png") ;
        pause = new Texture("pauseButton.png") ;
        pauseButton = new Sprite (pause) ;
        gameBackground = new Sprite (background) ;
        redBird = new Sprite (RedBird.image) ;
        blueBird = new Sprite (BlueBird.image) ;
        yellowBird = new Sprite (YellowBird.image) ;
        matilda = new Sprite (Maltida.image) ;
        blackBird  = new Sprite (BlackBird.image) ;
        bigRed = new Sprite (BigRed.image) ;
        slingShot = new Sprite (sling) ;
        slingShot.setSize(50,100);
        redBird.setSize(50, 50);
        blueBird.setSize(50, 30);
        yellowBird.setSize(50, 50);
        matilda.setSize(50, 50);
        blackBird.setSize(50, 50);
        bigRed.setSize(50, 50);
        gameBackground.setSize(800,500);
        gameBackground.setPosition(0,0);
        slingShot.setPosition(125 , 50 ) ;
        redBird.setPosition(100, 50);
        blueBird.setPosition(80, 50);
        yellowBird.setPosition(60, 50);
        matilda.setPosition(40, 50);
        blackBird.setPosition(20, 50);
        pauseButton.setSize(80, 80) ;
        pauseButton.setPosition(20, 430) ;
        rightButton.setSize(30,30) ;
        wrongButton.setSize(30,30) ;
        wrongButton.setPosition(20 , 10 ) ;
        rightButton.setPosition(750, 10) ;
        horizontal = new Texture("horizontal.png") ;
        vertical = new Texture("vertical.png") ;
        horizontalButton = new Sprite (horizontal) ;
        verticalButton = new Sprite (vertical) ;
        horizontalButton.setSize(100,15);
        verticalButton.setSize(15,100);

        //bigRed.setSize(50, 50);



    }



    @Override
    public void input() {
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            Vector2 touch = new Vector2();
            touch.set(x, y);
            Main.viewport.unproject(touch);

            if (pauseButton.getBoundingRectangle().contains(touch.x, touch.y)) {
                manager.set(new Pause(manager));
            }

            else if (wrongButton.getBoundingRectangle().contains(touch.x, touch.y)) {
                manager.set(new LosingScreen(manager)) ;
            }

            else if (rightButton.getBoundingRectangle().contains(touch.x,touch.y))
            {
                manager.set(new WinningScreen(manager)) ;
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
        gameBackground.draw(batch) ;
        slingShot.draw(batch) ;
        redBird.draw(batch);
        blueBird.draw(batch);
        yellowBird.draw(batch);
        matilda.draw(batch);
        blackBird.draw(batch);
        pauseButton.draw(batch) ;
        wrongButton.draw(batch);
        rightButton.draw(batch);
        verticalButton.setPosition(600 , 50);
        verticalButton.draw(batch);
        verticalButton.setPosition(685, 50 ) ;
        verticalButton.draw(batch);
        horizontalButton.setPosition(600 , 150);
        horizontalButton.draw(batch);
        piggo.setPosition(625 , 50 );
        piggo.draw(batch);
        verticalButton.setPosition(600 , 165);
        verticalButton.draw(batch);
        horizontalButton.setPosition(600 , 265);
        horizontalButton.draw(batch);
        verticalButton.setPosition(685 , 165);
        verticalButton.draw(batch);
        piggo.setPosition(625 , 155);
        piggo.draw(batch);



        //bigRed.draw(batch);

        batch.end();
    }

    @Override
    public void dispose() {

    }
}
