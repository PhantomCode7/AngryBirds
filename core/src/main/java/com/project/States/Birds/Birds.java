package com.project.States.Birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Birds {
    protected Sprite sprite;
    protected Vector2 position;
    protected Vector2 velocity;
    protected Rectangle bounds;
    protected float impactPower; // New property for impact power

    public Birds(String texturePath, float width, float height, Vector2 initialPosition, float impactPower) {
        Texture texture = new Texture(texturePath);
        sprite = new Sprite(texture);
        sprite.setSize(width, height);
        position = new Vector2(initialPosition);
        sprite.setPosition(position.x, position.y);
        bounds = new Rectangle(position.x, position.y, width, height);
        velocity = new Vector2(0, 0);
        this.impactPower = impactPower;
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
        sprite.setPosition(x, y);
        bounds.setPosition(x, y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity.set(velocity);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public float getImpactPower() { // New getter for impact power
        return impactPower;
    }

    public void setImpactPower(float impactPower) { // Optional setter
        this.impactPower = impactPower;
    }

    public void updatePosition(float delta) {
        position.x += velocity.x * delta;
        position.y += velocity.y * delta;
        sprite.setPosition(position.x, position.y);
        bounds.setPosition(position.x, position.y);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public float getWidth() {
        return sprite.getWidth();
    }

    public float getHeight() {
        return sprite.getHeight();
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }

    // New method for special abilities (default to no action)
    public void applySpecialAbility() {
        // No default behavior, to be overridden by subclasses if needed
    }
}
