package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;

public class BlackBird extends Birds {

    public BlackBird(Vector2 initialPosition) {
        super("bomb.png", 45, 45, initialPosition, 80); // High impact power
    }

    // Special ability: Explode on command
    @Override
    public void applySpecialAbility() {
        System.out.println("BlackBird exploded!");
        // Implement logic to deal area damage to nearby objects
    }
}
