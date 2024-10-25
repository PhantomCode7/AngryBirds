package com.project.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WinningScreen extends State{

    Texture win ;
    Sprite levelCleared ;
    Texture replay ;
    Sprite replayButton ;
    Texture nextLevel ;
    Sprite nextLevelButton ;
    public WinningScreen(StateManager manager) {
        super(manager);
        win = new Texture("levelCleared.png");
        levelCleared = new Sprite(win);
        replay = new Texture("replayButton.png");
        replayButton = new Sprite(replay);
        nextLevel = new Texture("nextLevel.png");
        nextLevelButton = new Sprite(nextLevel);

        levelCleared.setSize(800 , 500);
        levelCleared.setPosition(0, 0);
        replayButton.setSize(80 , 80);
        replayButton.setPosition(300 , 120) ;
        nextLevelButton.setSize(80 , 80);
        nextLevelButton.setPosition(450 , 120); ;
    }

    @Override
    public void input() {
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            Vector2 touch = new Vector2();
            touch.set(x, y);
            Main.viewport.unproject(touch);
            if (nextLevelButton.getBoundingRectangle().contains(touch.x, touch.y) || replayButton.getBoundingRectangle().contains(touch.x, touch.y)) {
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
        batch.begin() ;
        levelCleared.draw(batch);
        replayButton.draw(batch);
        nextLevelButton.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
