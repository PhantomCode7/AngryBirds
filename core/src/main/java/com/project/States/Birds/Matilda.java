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


            Vector2 eggPosition = new Vector2(position.x, position.y - 20);
            Birds egg = new Bomb(eggPosition, impactDamage * 2);
            GameScreen.getBirds().add(egg);
        }
    }
}
