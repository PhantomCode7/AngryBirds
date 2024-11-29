package com.project.States.Materials;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.*;

public abstract class Materials implements Serializable {
    private static final long serialVersionUID = 1L;

    protected transient Sprite sprite;
    protected String texturePath;
    protected Rectangle bounds;
    protected int health;

    public Materials(String texturePath, int width, int height, Vector2 position, int health) {
        this.texturePath = texturePath;
        this.bounds = new Rectangle(position.x, position.y, width, height);
        this.health = health;

        reloadSprite();
    }

    private void reloadSprite() {
        try {
            Texture texture = new Texture(texturePath);
            sprite = new Sprite(texture);
            sprite.setSize(bounds.width, bounds.height);
            sprite.setPosition(bounds.x, bounds.y);
        } catch (Exception e) {
            System.err.println("Failed to reload sprite for texture: " + texturePath);
            e.printStackTrace();
        }
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
        if (health < 0) health = 0;
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        reloadSprite();
    }

    public int getHealth() {
        return health;
    }
}
