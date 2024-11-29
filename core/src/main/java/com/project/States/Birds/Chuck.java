package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class Chuck extends Birds implements Serializable {
    private static final long serialVersionUID = 1L;

    public Chuck(Vector2 initialPosition) {
        super("chuck.png", 50, 50, initialPosition, 75);
    }

    @Override
    protected void triggerAbility() {

        velocity.scl(2.0f);
    }

    public void reloadSprite() {
        super.reloadSprite();
    }
}
