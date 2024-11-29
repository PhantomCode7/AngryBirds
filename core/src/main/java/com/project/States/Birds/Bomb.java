package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;
import com.project.States.Screens.GameScreen;
import com.project.States.Materials.Materials;
import com.project.States.Pigs.Pig;

import java.io.Serializable;

public class Bomb extends Birds implements Serializable {
    private static final long serialVersionUID = 1L;
    private int explosionRadius;

    public Bomb(Vector2 initialPosition, int impactDamage) {
        super("bomb.png", 50, 50, initialPosition, impactDamage);
        this.explosionRadius = 100;
    }

    @Override
    protected void triggerAbility() {
        System.out.println("Bomb exploded");
    }

    public void reloadSprite() {
        super.reloadSprite();
    }
}
