package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;

public class Chuck extends Birds {

    public Chuck(Vector2 initialPosition) {
        super("chuck.png", 50, 50, initialPosition, 75);
    }

    @Override
    protected void triggerAbility() {
        // Double the bird's velocity for a speed boost
        velocity.scl(2.0f);
    }
}
