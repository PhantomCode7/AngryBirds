package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;
import com.project.States.Screens.GameScreen;

public class Matilda extends Birds {
    private boolean eggDropped;

    public Matilda(Vector2 initialPosition, int impactDamage) {
        super("matilda.png", 50, 50, initialPosition, impactDamage);
        this.eggDropped = false;
    }

    @Override
    protected void triggerAbility() {
        if (!eggDropped) {
            System.out.println("Matilda drops an egg!");
            eggDropped = true;

            // Drop an egg directly below Matilda
            Vector2 eggPosition = new Vector2(position.x, position.y - 20); // Position below Matilda
            Birds egg = new Bomb(eggPosition, impactDamage * 2); // Egg acts as a Bomb bird
            GameScreen.getBirds().add(egg); // Add the egg to the game (requires managing GameScreen's bird list)
        }
    }
}
