package com.project.States.Materials;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Materials {
    protected Sprite sprite;
    protected Vector2 position;
    protected Rectangle bounds;
    protected int hp;

    public Materials(String texturePath, float width, float height, Vector2 initialPosition, int hp) {
        Texture texture = new Texture(texturePath);
        sprite = new Sprite(texture);
        sprite.setSize(width, height);
        position = new Vector2(initialPosition);
        sprite.setPosition(position.x, position.y);
        bounds = new Rectangle(position.x, position.y, width, height);
        this.hp = hp;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
        sprite.setPosition(x, y);
        bounds.setPosition(x, y);
    }

    /**
     * Apply damage to the material.
     * @param damage Amount of damage to apply.
     */
    public void takeDamage(int damage) {
        hp -= damage;
    }

    /**
     * Check if the material is destroyed.
     * @return True if HP <= 0, else False.
     */
    public boolean isDestroyed() {
        return hp <= 0;
    }

    /**
     * Draw the material on the screen.
     * @param batch SpriteBatch used for drawing.
     */
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    /**
     * Dispose of the material's texture to free resources.
     */
    public void dispose() {
        sprite.getTexture().dispose();
    }
}
