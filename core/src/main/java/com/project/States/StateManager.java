package com.project.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class StateManager {

    private Stack<State> states;

    public StateManager() {
        states = new Stack<>();
    }

    public void push(State state) {
        if (!states.isEmpty()) {
            states.peek().pause();
        }
        states.push(state);
    }

    public void pop() {
        if (!states.isEmpty()) {
            State oldState = states.pop();
            oldState.dispose();
            if (!states.isEmpty()) {
                states.peek().resume();
            }
        }
    }

    public void set(State state) {
        if (!states.isEmpty()) {
            State oldState = states.pop();
            if (oldState != state) { // Avoid disposing and re-adding the same state
                oldState.dispose();
            }
        }
        states.push(state);
    }

    public State peek() {
        return states.isEmpty() ? null : states.peek();
    }

    public void clear() {
        while (!states.isEmpty()) {
            states.pop().dispose();
        }
    }

    public void update(float delta) {
        if (!states.isEmpty()) {
            states.peek().update(delta);
        }
    }

    public void render(SpriteBatch batch) {
        if (!states.isEmpty()) {
            states.peek().render(batch);
        }
    }
}
