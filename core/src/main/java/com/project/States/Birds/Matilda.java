package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;

public class Matilda extends Birds {

    public Matilda(Vector2 initialPosition) {
        super("matilda_egg.png", 30, 30, initialPosition, 80); // High impact power
    }

    // Special ability: lay egggs on command
    @Override
    public void applySpecialAbility() {
        System.out.println("Matilda egged!");
        // Implement logic to deal area damage to nearby objects
    }
}
