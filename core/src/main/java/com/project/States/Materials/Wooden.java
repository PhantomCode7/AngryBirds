package com.project.States.Materials;

import com.badlogic.gdx.math.Vector2;

public class Wooden extends Materials {

    public Wooden(String texturePath, Vector2 initialPosition) {
        super(texturePath, 100, 20, initialPosition, 50); // Wooden has 50 HP
    }

    // Additional behaviors specific to Wooden can be added here
}
