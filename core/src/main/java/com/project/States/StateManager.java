package com.project.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class StateManager {

    private Stack<State> states;
    public StateManager ()
    {
        states = new Stack<State> ();
    }

    public void push(State state) {
        if (!states.isEmpty()) {
            states.peek().pause();
        }
        states.push(state);
    }

    public void pop() {
        State oldState = states.pop();
        oldState.dispose();
        if (!states.isEmpty()) {
            states.peek().resume();
        }
    }

    public void set(State state) {
        State oldState = states.pop();
        oldState.dispose();
        states.push(state);
    }

    public void update (float delta)
    {
        states.peek().update(delta) ;
    }

    public void render (SpriteBatch Batch)
    {
        states.peek().render(Batch ) ;
    }
}
