package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;

public class BlueBird extends Birds {

    public BlueBird(Vector2 initialPosition) {
        super("blue_bird.png", 40, 40, initialPosition, 50); // Lower impact power (e.g., 50)
    }

    // Special ability: Split into three smaller birds (logic to be implemented in the game loop)
    @Override
    public void applySpecialAbility() {
        // Logic for splitting the bird mid-flight
    }
}
