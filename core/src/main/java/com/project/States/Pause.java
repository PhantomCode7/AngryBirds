package com.project.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.project.States.Screens.GameScreen;
import com.project.States.Screens.MainScreen;

public class Pause extends State{
    Texture pause ;
    Texture resume ;
    Texture back  ;
    Sprite pauseButton ;
    Sprite resumeButton ;
    Sprite backButton ;

    public Pause(StateManager manager) {
        super(manager);
        pause = new Texture ("Pause.png") ;
        resume = new Texture ("resume.png") ;
        back = new Texture ("backToMenu.png") ;
        pauseButton = new Sprite (pause) ;
        resumeButton = new Sprite(resume) ;
        backButton = new Sprite (back) ;

        pauseButton.setSize(800,500) ;
        pauseButton.setPosition(0,0);
        resumeButton.setPosition(350, 275) ;
        resumeButton.setSize(100,50);
        backButton.setPosition(350 , 175);
        backButton.setSize(100 , 50) ;
    }

    @Override
    public void input() {
        if (Gdx.input.justTouched())
        {
            float x = Gdx.input.getX() ;
            float y =   Gdx.input.getY() ;

            Vector2 touch = new Vector2() ;
            touch.set(x,y) ;
            Main.viewport.unproject(touch) ;
            if (backButton.getBoundingRectangle().contains(touch.x , touch.y))
            {
                manager.set(new MainScreen(manager)) ;
            }
            else if (resumeButton.getBoundingRectangle().contains(touch.x,touch.y))
            {
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
        pauseButton.draw(batch) ;
        resumeButton.draw(batch) ;
        backButton.draw(batch) ;
        batch.end() ;

    }

    @Override
    public void dispose() {
        if (pause != null) pause.dispose();
        if (resume != null) resume.dispose();
        if (back != null) back.dispose();
    }
}
