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

    public Birds(String texturePath, float width, float height, Vector2 initialPosition) {
        Texture texture = new Texture(texturePath);
        sprite = new Sprite(texture);
        sprite.setSize(width, height);
        position = new Vector2(initialPosition);
        sprite.setPosition(position.x, position.y);
        bounds = new Rectangle(position.x, position.y, width, height);
        velocity = new Vector2(0, 0);
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

    /**
     * Update bird's position based on its velocity and delta time.
     * @param delta Time elapsed since last frame.
     */
    public void updatePosition(float delta) {
        position.x += velocity.x * delta;
        position.y += velocity.y * delta;
        sprite.setPosition(position.x, position.y);
        bounds.setPosition(position.x, position.y);
    }

    /**
     * Draw the bird on the screen.
     * @param batch SpriteBatch used for drawing.
     */
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    /**
     * Get the width of the bird's sprite.
     * @return Width of the sprite.
     */
    public float getWidth() {
        return sprite.getWidth();
    }

    /**
     * Get the height of the bird's sprite.
     * @return Height of the sprite.
     */
    public float getHeight() {
        return sprite.getHeight();
    }

    /**
     * Dispose of the bird's texture to free resources.
     */
    public void dispose() {
        sprite.getTexture().dispose();
    }
}
