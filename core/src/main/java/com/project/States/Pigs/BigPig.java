package com.project.States.Pigs;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class BigPig extends Pig implements Serializable {
    private static final long serialVersionUID = 1L;

    public BigPig(Vector2 initialPosition) {
        super("Piggy_medium.png", 50, 50, initialPosition, 400);
    }


}
