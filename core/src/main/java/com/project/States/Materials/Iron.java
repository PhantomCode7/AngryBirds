package com.project.States.Materials;

import com.badlogic.gdx.math.Vector2;

public class Iron extends Materials {

    public Iron(String texturePath, Vector2 initialPosition) {
        super(texturePath, 100, 20, initialPosition, 100); // Iron has 100 HP
    }

    // Additional behaviors specific to Iron can be added here
}
