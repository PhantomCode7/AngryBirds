package com.project.States.Materials;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Materials {
    protected Sprite sprite;
    protected Rectangle bounds;
    protected int health;

    public Materials(String texturePath, int width, int height, Vector2 position, int health) {
        this.sprite = new Sprite(new Texture(texturePath));
        this.sprite.setSize(width, height);
        this.sprite.setPosition(position.x, position.y);
        this.bounds = new Rectangle(position.x, position.y, width, height);
        this.health = health;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isDestroyed() {
        return health <= 0;
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println("Material hit! Remaining health: " + health);
        if (health < 0) health = 0; // Ensure health doesn't go negative
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }
}
