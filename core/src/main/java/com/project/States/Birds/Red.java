package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;

public class Red extends Birds {

    public Red(Vector2 initialPosition) {
        super("red.png", 50, 50, initialPosition, 50);
    }

    @Override
    protected void triggerAbility() {

        System.out.println("Red bird does not have a special ability");
    }
}
