package com.project.States.Materials;

import com.badlogic.gdx.math.Vector2;

public class Wooden extends Materials {

    public Wooden(String texturePath, int width , int height , Vector2 initialPosition) {
        super(texturePath, width, height, initialPosition, 60); // Wooden has 50 HP
    }

    // Additional behaviors specific to Wooden can be added here
}
