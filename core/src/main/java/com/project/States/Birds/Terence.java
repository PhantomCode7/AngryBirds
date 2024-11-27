package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;

public class Terence extends Birds {

    public Terence(Vector2 initialPosition) {
        super("terence.png", 70, 70, initialPosition, 200);
    }

    @Override
    public void activateAbility() {
        // Terence does not have a special ability but has high impact
        System.out.println("Terence smashes through with high impact!");
    }
}
