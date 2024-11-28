package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;
import com.project.States.Screens.GameScreen;

import java.io.Serializable;

public class Matilda extends Birds implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean eggDropped;

    public Matilda(Vector2 initialPosition, int impactDamage) {
        super("matilda.png", 50, 50, initialPosition, impactDamage);
        this.eggDropped = false;
    }

    @Override
    protected void triggerAbility() {
        System.out.println("Matilda dropped an egg");
        this.eggDropped = true;
    }

    public void reloadSprite() {
        super.reloadSprite();
    }
}
