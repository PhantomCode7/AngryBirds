package com.project.States.Materials;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class Glass extends Materials implements Serializable {
    private static final long serialVersionUID = 1L;

    // Glass has 30 HP
    public Glass(String texturePath, int width , int height , Vector2 initialPosition) {
        super(texturePath, width, height ,  initialPosition, 30);
    }


}
