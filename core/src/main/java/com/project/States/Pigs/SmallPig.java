package com.project.States.Pigs;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class SmallPig extends Pig implements Serializable {
    private static final long serialVersionUID = 1L;

    public SmallPig(Vector2 initialPosition) {
        super("Piggy_medium.png", 40, 40, initialPosition, 500);
    }


}
