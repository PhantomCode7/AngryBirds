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
    protected int health; // Health or hitpoints of the pig

    public Pig(String texturePath, float width, float height, Vector2 initialPosition, int health) {
        Texture texture = new Texture(texturePath);
        sprite = new Sprite(texture);
        sprite.setSize(width, height);
        position = new Vector2(initialPosition);
        sprite.setPosition(position.x, position.y);
        bounds = new Rectangle(position.x, position.y, width, height);
        this.health = health;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println("Pig hit! Remaining health: " + health); // Debugging message
        if (health < 0) health = 0; // Ensure health doesn’t go negative
    }

    public boolean isDestroyed() {
        return health <= 0;
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
        sprite.setPosition(x, y);
        bounds.setPosition(x, y);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }
}
