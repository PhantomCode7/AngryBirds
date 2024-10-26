package com.project.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class LosingScreen extends State{

    Texture image;
    Sprite LosingScreen ;
    Texture back ;
    Sprite backButton ;
    Texture replay ;
    Sprite replayButton ;


    public LosingScreen(StateManager manager) {
        super(manager);

        image = new Texture("LosingScreen.png");
        LosingScreen = new Sprite(image);
        back = new Texture("Lexit.png");
        backButton = new Sprite(back);
        replay = new Texture("replayButton.png");
        replayButton = new Sprite(replay);

        LosingScreen.setPosition(0,0);
        LosingScreen.setSize(800 , 500) ;
        backButton.setPosition(275 , 25 );
        backButton.setSize(80 , 80) ;
        replayButton.setSize(80 , 80) ;
        replayButton.setPosition(425 , 25) ;


    }

    @Override
    public void input() {
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            Vector2 touch = new Vector2();
            touch.set(x, y);
            Main.viewport.unproject(touch);

            if (backButton.getBoundingRectangle().contains(touch.x, touch.y)) {

                manager.set(new MainScreen(manager));
            }

            else if (replayButton.getBoundingRectangle().contains(touch.x, touch.y)) {
                manager.set(new GameScreen(manager));
            }

        }
    }

    @Override
    public void update(float delta) {
        input() ;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        LosingScreen.draw(batch);
        replayButton.draw(batch);
        backButton.draw(batch);
        batch.end();

    }

    @Override
    public void dispose() {
        if (image != null) image.dispose();
        if (back != null) back.dispose();
        if (replay != null) replay.dispose();

    }
}
