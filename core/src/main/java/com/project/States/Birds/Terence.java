package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class Terence extends Birds implements Serializable {
    private static final long serialVersionUID = 1L;

    public Terence(Vector2 initialPosition) {
        super("terence.png", 70, 70, initialPosition, 200);
    }

    @Override
    public void activateAbility() {
        System.out.println("Terence smashes through with high impact!");
    }

    public void reloadSprite() {
        super.reloadSprite();
    }
}
