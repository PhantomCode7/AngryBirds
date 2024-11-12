package com.project.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class StateManager {

    private Stack<State> states;
    public StateManager ()
    {
        states = new Stack<State> ();
    }

    public void push (State state)
    {
        states.push(state);
    }

    public void pop () {
        states.pop() ;
    }

    public void update (float delta)
    {
        states.peek().update(delta) ;
    }

    public void render (SpriteBatch Batch)
    {
        states.peek().render(Batch ) ;
    }

    public void set (State state)
    {
        states.pop() ;
        states.push(state) ;
    }
}
