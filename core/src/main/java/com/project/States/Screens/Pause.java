package com.project.States.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.project.States.Main;
import com.project.States.State;
import com.project.States.StateManager;
import com.project.States.MusicManager;

public class Pause extends State {
    Texture pause ;
    Texture resume ;
    Texture back  ;
    Sprite pauseButton ;
    Sprite resumeButton ;
    Sprite backButton ;

    private final GameScreen gameScreen; // Store the current GameScreen instance
    private final Level2 level2  ;
    private final Level3 level3  ;


    public Pause(StateManager manager, GameScreen gameScreen) {
        super(manager);
        this.gameScreen = gameScreen; // Save the reference
        this.level2 = null ;
        this.level3 = null ;

        pause = new Texture("Pause.png");
        resume = new Texture("resume.png");
        back = new Texture("backToMenu.png");

        pauseButton = new Sprite(pause);
        resumeButton = new Sprite(resume);
        backButton = new Sprite(back);

        pauseButton.setSize(800, 500);
        pauseButton.setPosition(0, 0);
        resumeButton.setPosition(350, 275);
        resumeButton.setSize(100, 50);
        backButton.setPosition(350, 175);
        backButton.setSize(100, 50);


        MusicManager.getInstance().pauseMusic();
    }

    public Pause(StateManager manager, Level2 gameScreen) {
        super(manager);
        this.gameScreen = null;
        this.level2 = gameScreen;

        this.level3 = null ;

        pause = new Texture("Pause.png");
        resume = new Texture("resume.png");
        back = new Texture("backToMenu.png");

        pauseButton = new Sprite(pause);
        resumeButton = new Sprite(resume);
        backButton = new Sprite(back);

        pauseButton.setSize(800, 500);
        pauseButton.setPosition(0, 0);
        resumeButton.setPosition(350, 275);
        resumeButton.setSize(100, 50);
        backButton.setPosition(350, 175);
        backButton.setSize(100, 50);

        MusicManager.getInstance().pauseMusic();
    }

    public Pause(StateManager manager, Level3 gameScreen) {
        super(manager);
        this.gameScreen = null;
        this.level3 = gameScreen;
        this.level2 = null;

        pause = new Texture("Pause.png");
        resume = new Texture("resume.png");
        back = new Texture("backToMenu.png");

        pauseButton = new Sprite(pause);
        resumeButton = new Sprite(resume);
        backButton = new Sprite(back);

        pauseButton.setSize(800, 500);
        pauseButton.setPosition(0, 0);
        resumeButton.setPosition(350, 275);
        resumeButton.setSize(100, 50);
        backButton.setPosition(350, 175);
        backButton.setSize(100, 50);

        MusicManager.getInstance().pauseMusic();
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
            if (backButton.getBoundingRectangle().contains(touch.x, touch.y)) {
                manager.set(new MainScreen(manager)); // Go back to the main menu
            } else if (resumeButton.getBoundingRectangle().contains(touch.x, touch.y)) {
                if (Main.a==1) {
                    manager.set(gameScreen);
                }
                else if (Main.a==2) {
                    manager.set(level2);
                }

                else if (Main.a ==3 )
                {
                    manager.set(level3);
                }

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
        MusicManager.getInstance().resumeMusic();
    }
}
