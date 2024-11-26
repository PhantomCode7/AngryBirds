package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;

public class BigRed extends Birds {

    public BigRed(Vector2 initialPosition) {
        super("big_red.png", 50, 50, initialPosition, 100); // High impact power
    }

    // Special ability: Increase speed mid-flight
    @Override
    public void applySpecialAbility() {
        if (velocity.len() > 0) { // If bird is already moving
            velocity.scl(1.5f); // Increase speed by 50%
        }
    }
}
