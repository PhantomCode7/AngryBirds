package com.project.States.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.project.States.*;
import com.project.States.Birds.*;
import com.project.States.Materials.Iron;
import com.project.States.Materials.Wooden;
import com.project.States.Pigs.MediumPig;
import com.project.States.Pigs.SmallPig;


public class GameScreen extends State {


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
    MediumPig mediumPig ;
    SmallPig smallPig ;
    Texture pig2 ;
    Sprite pigImage ;
    Wooden wood ;
    Iron iron ;
    Texture ironHorizontal ;
    Texture ironVertical ;
    Sprite horizontal1 ;
    Sprite vertical1 ;
    RedBird redBirdObject ;
    BlueBird blueBirdObject ;
    YellowBird yellowBirdObject ;
    BlackBird blackBirdObject ;
    BigRed bigRedObject ;
    Maltida maltidaObject ;






    public GameScreen(StateManager manager) {
        super(manager);
        redBirdObject = new RedBird();
        blueBirdObject = new BlueBird();
        yellowBirdObject = new YellowBird();
        blackBirdObject = new BlackBird();
        bigRedObject = new BigRed();
        maltidaObject = new Maltida();
        pig2 = new Texture("Piggy_medium.png") ;
        pigImage = new Sprite(pig2);
        wood = new Wooden();
        iron = new Iron();
        mediumPig = new MediumPig() ;
        smallPig = new SmallPig() ;
        pigImage.setSize(smallPig.getWidth(), smallPig.getHeight());
        wrong = new Texture ("wrong.png") ;
        right = new Texture ("right.png") ;
        pig = new Texture("Piggy_medium.png") ;
        piggo = new Sprite (pig) ;
        piggo.setSize(mediumPig.getWidth(), mediumPig.getHeight()) ;
        rightButton = new Sprite(right) ;
        wrongButton = new Sprite(wrong) ;
        background = new Texture("gameBackground.png") ;
        sling = new Texture ("slingShot.png") ;
        pause = new Texture("pauseButton.png") ;
        pauseButton = new Sprite (pause) ;
        gameBackground = new Sprite (background) ;
        redBird = new Sprite (RedBird.getImage()) ;
        blueBird = new Sprite (BlueBird.getImage());
        yellowBird = new Sprite (YellowBird.getImage());
        matilda = new Sprite (Maltida.getImage());
        blackBird  = new Sprite (BlackBird.getImage());
        bigRed = new Sprite (BigRed.getImage());
        slingShot = new Sprite (sling) ;
        slingShot.setSize(50,100);
        redBird.setSize(redBirdObject.getWidth(), redBirdObject.getHeight());
        blueBird.setSize(blueBirdObject.getWidth(), blueBirdObject.getHeight());
        yellowBird.setSize(yellowBirdObject.getWidth(), yellowBirdObject.getHeight());
        matilda.setSize(maltidaObject.getWidth(), maltidaObject.getHeight());
        blackBird.setSize(blackBirdObject.getWidth(), blackBirdObject.getHeight());
        bigRed.setSize(bigRedObject.getWidth(), bigRedObject.getHeight());
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
        horizontal = Wooden.getHorizontal() ;
        vertical = Wooden.getVertical() ;
        horizontalButton = new Sprite (horizontal) ;
        verticalButton = new Sprite (vertical) ;
        ironHorizontal = Iron.getHorizontal() ;
        ironVertical = Iron.getVertical() ;
        horizontalButton.setSize(wood.getHorizontalWidth(), wood.getHorizontalHeight());
        verticalButton.setSize(wood.getVerticalWidth(), wood.getVerticalHeight());
        horizontal1 = new Sprite (ironHorizontal) ;
        vertical1 = new Sprite (ironVertical) ;
        horizontal1.setSize(iron.getHorizontalWidth(), iron.getHorizontalHeight());
        vertical1.setSize(iron.getHorizontalWidth(), iron.getHorizontalHeight());

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
        vertical1.setPosition(500 , 50) ;
        horizontal1.setPosition(500 , 150);
        vertical1.draw(batch);
        horizontal1.draw(batch);
        pigImage.setPosition(545 , 50 ) ;
        pigImage.draw(batch);
        piggo.draw(batch);



        //bigRed.draw(batch);

        batch.end();
    }

    @Override
    public void dispose() {
        if (wrong != null) wrong.dispose();
        if (right != null) right.dispose();
        if (background != null) background.dispose();
        if (sling != null) sling.dispose();
        if (pause != null) pause.dispose();
        if (horizontal != null) horizontal.dispose();
        if (vertical != null) vertical.dispose();
        if (pig != null) pig.dispose();
        if (pig2 != null) pig2.dispose();
    }
}
