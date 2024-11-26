package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;

public class YellowBird extends Birds {

    public YellowBird(Vector2 initialPosition) {
        super("yellow_bird.png", 40, 40, initialPosition, 40); // Moderate impact power
    }

    // Special ability: Gain extra speed mid-flight
    @Override
    public void applySpecialAbility() {
        if (velocity.len() > 0) {
            velocity.scl(2.0f); // Double speed
        }
    }
}
