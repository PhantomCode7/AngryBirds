package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;
import com.project.States.Screens.GameScreen;
import com.project.States.Materials.Materials;
import com.project.States.Pigs.Pig;

import java.io.Serializable;

public class Bomb extends Birds implements Serializable {
    private static final long serialVersionUID = 1L;
    private int explosionRadius;

    public Bomb(Vector2 initialPosition, int impactDamage) {
        super("bomb.png", 50, 50, initialPosition, impactDamage);
        this.explosionRadius = 100; // Set the radius of the explosion
    }

    @Override
    protected void triggerAbility() {
        GameScreen gameScreen = GameScreen.getInstance(); // Singleton or pass reference
        if (gameScreen != null) {
            // Damage surrounding materials
            for (int i = 0; i < GameScreen.getMaterials().size(); i++) {
                Materials material = GameScreen.getMaterials().get(i);
                if (isWithinExplosionRange(material.getBounds().getCenter(new Vector2()))) {
                    System.out.println("Material hit by explosion: " + material); // Debug log
                    material.takeDamage(impactDamage * 2); // Apply damage
                    if (material.isDestroyed()) {
                        System.out.println("Material destroyed: " + material); // Debug log
                        GameScreen.getMaterials().remove(i); // Remove if destroyed
                        i--; // Adjust index after removal
                    }
                }
            }


            // Damage surrounding pigs
            for (Pig pig : GameScreen.getPigs()) {
                if (isWithinExplosionRange(pig.getBounds().getCenter(new Vector2()))) {
                    pig.takeDamage(impactDamage * 2); // Double damage for explosion
                    if (pig.isDestroyed()) {
                        GameScreen.getPigs().remove(pig);
                    }
                }
            }
        }
    }

    private boolean isWithinExplosionRange(Vector2 targetPosition) {
        float distance = position.dst(targetPosition); // Distance between bomb and target
        return distance <= explosionRadius; // Check if within radius
    }

    public void reloadSprite() {
        super.reloadSprite();
    }
}
