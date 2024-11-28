package com.project.States.Pigs;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class MediumPig extends Pig implements Serializable {
    private static final long serialVersionUID = 1L;

    public MediumPig(Vector2 initialPosition) {
        super("Piggy_medium.png", 40, 40, initialPosition, 100);
    }

    // Additional behaviors specific to SmallPig can be added here
}
