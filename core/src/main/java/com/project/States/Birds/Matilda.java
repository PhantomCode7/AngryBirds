package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;

public class Matilda extends Birds {

    private boolean hasDroppedEgg = false; // Track if the egg has been dropped

    public Matilda(Vector2 initialPosition) {
        super("matilda_egg.png", 40, 40, initialPosition, 40); // Moderate impact power
    }

    @Override
    public void applySpecialAbility() {
        if (!hasDroppedEgg) {
            System.out.println("Matilda dropped an egg bomb!");

            // Logic to drop the egg
            dropEgg();

            hasDroppedEgg = true; // Ensure the egg is dropped only once
        }
    }

    private void dropEgg() {
        // Create a new Egg object and launch it downward
        Egg egg = new Egg(getPosition().cpy());
        egg.setVelocity(new Vector2(0, -300)); // Drop the egg straight down with high speed

        // Add the egg to the game world (replace with your game logic)
        // Example: gameWorld.add(egg);
    }
}

/**
 * Egg Class - Represents the egg bomb dropped by Matilda.
 */
class Egg extends Birds {

    public Egg(Vector2 initialPosition) {
        super("egg.png", 20, 20, initialPosition, 60); // Egg impact power
    }

    @Override
    public void applySpecialAbility() {
        // Eggs do not have further abilities
    }

    @Override
    public void updatePosition(float delta) {
        super.updatePosition(delta);

        // Check for collision with ground, pigs, or materials
        checkCollision();
    }

    private void checkCollision() {
        // Logic to deal damage to objects on collision
        // Example: On collision with a pig or material, apply `impactPower` damage and destroy the egg
    }
}
