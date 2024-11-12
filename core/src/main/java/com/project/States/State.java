package com.project.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class State {

    OrthographicCamera camera ;
    public StateManager manager ;
    Vector2 v ;

    public State (StateManager manager )
    {
        this.manager = manager ;
        this.camera = new OrthographicCamera() ;
        this.v = new Vector2() ;
    }

    public abstract void input() ;
    public abstract void update (float delta) ;
    public abstract void render (SpriteBatch batch) ;
    public abstract void dispose() ;


}
