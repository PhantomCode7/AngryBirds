package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;

public class BlueBird extends Birds {

    public BlueBird(Vector2 initialPosition) {
        super("blue_bird.png", 35, 35, initialPosition, 30); // Low impact power
    }

    // Special ability: Split into three smaller birds
    @Override
    public void applySpecialAbility() {
        // Logic for splitting: spawn additional birds in the game world
        System.out.println("BlueBird is splitting into three!");
        // Implement logic to add two additional smaller birds to the game
    }
}
