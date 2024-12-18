package com.project.States;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.project.States.Screens.LoginScreen;

import java.io.*;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    public static int levelCleared = 1 ;
    private Texture image;
    public static FitViewport viewport ;
    public static int a = 1 ;
    Sprite sprite ;
    StateManager manager ;

    public void resize(int width , int height  )
    {
        viewport.update(width , height , true ) ;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("wallpaper.png");
        viewport = new FitViewport(800,500) ;
        sprite = new Sprite (image) ;
        sprite.setSize(800,500) ;
        sprite.setPosition(0,0);
        manager = new StateManager() ;
        File  file = new File("LevelNumber.txt");
        if (file.exists())
        {
            String filename = file.getName() ;
            try {
                 FileInputStream is = new FileInputStream(filename);
                ObjectInputStream fis = new ObjectInputStream(is);
                a = ( int ) fis.readObject() ;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        manager.push(new LoginScreen(manager)) ;
        MusicManager.getInstance().playMusic();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0f, 0, 0, 1f);
        viewport.apply();
        viewport.getCamera().update();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        manager.update(Gdx.graphics.getDeltaTime()) ;
        manager.render(batch) ;
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        MusicManager.getInstance().dispose();
    }
}
