package com.project.States.Pigs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Pig {
    protected Sprite sprite;
    protected Vector2 position;
    protected Rectangle bounds;

    public Pig(String texturePath, float width, float height, Vector2 initialPosition) {
        Texture texture = new Texture(texturePath);
        sprite = new Sprite(texture);
        sprite.setSize(width, height);
        position = new Vector2(initialPosition);
        sprite.setPosition(position.x, position.y);
        bounds = new Rectangle(position.x, position.y, width, height);
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
     * Draw the pig on the screen.
     * @param batch SpriteBatch used for drawing.
     */
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    /**
     * Dispose of the pig's texture to free resources.
     */
    public void dispose() {
        sprite.getTexture().dispose();
    }
}
