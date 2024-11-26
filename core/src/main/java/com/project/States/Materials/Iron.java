package com.project.States.Materials;

import com.badlogic.gdx.math.Vector2;

public class Iron extends Materials {

    public Iron(String texturePath, int width , int height , Vector2 initialPosition) {
        super(texturePath, width, height ,  initialPosition, 100); // Iron has 100 HP
    }

    // Additional behaviors specific to Iron can be added here
}
