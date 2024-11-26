package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;

public class RedBird extends Birds {

    public RedBird(Vector2 initialPosition) {
        super("red_bird.png", 40, 40, initialPosition, 50); // Moderate impact power
    }

    // No special ability for RedBird
    @Override
    public void applySpecialAbility() {
        // No action
    }
}
