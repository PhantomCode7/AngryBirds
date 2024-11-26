package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.project.States.Materials.Materials;
import com.project.States.Pigs.Pig;

public class BlackBird extends Birds {

    public BlackBird(Vector2 initialPosition) {
        super("bomb.png", 45, 45, initialPosition, 80); // High impact power
    }

    @Override
    public void applySpecialAbility() {
        System.out.println("Bomb exploded!");

        // Logic to deal area damage
        float explosionRadius = 100f; // Define the explosion radius
        Array<Object> affectedObjects = findNearbyObjects(explosionRadius);

        for (Object obj : affectedObjects) {
            if (obj instanceof Pig) {
                ((Pig) obj).takeDamage(100); // Deal high damage to pigs
            } else if (obj instanceof Materials) {
                ((Materials) obj).takeDamage(50); // Deal lower damage to materials
            }
        }

        // After explosion, set velocity to zero
        setVelocity(new Vector2(0, 0));
    }

    /**
     * Placeholder method to find objects within the explosion radius.
     * Replace with your collision detection logic.
     */
    private Array<Object> findNearbyObjects(float radius) {
        // Implement logic to find nearby pigs and materials
        return new Array<>();
    }
}
