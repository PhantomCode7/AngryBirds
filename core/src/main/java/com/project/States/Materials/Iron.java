package com.project.States.Materials;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class Iron extends Materials implements Serializable {
    private static final long serialVersionUID = 1L;

    public Iron(String texturePath, int width , int height , Vector2 initialPosition) {
        super(texturePath, width, height ,  initialPosition, 100); // Iron has 100 HP
    }
}
