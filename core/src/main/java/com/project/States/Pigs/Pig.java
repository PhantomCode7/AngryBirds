package com.project.States.Pigs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.io.*;

public abstract class Pig implements Serializable {
    private static final long serialVersionUID = 1L;

    protected transient Sprite sprite;
    protected String texturePath;
    protected Vector2 position;
    protected Rectangle bounds;
    private int health;
    private float width;
    private float height;

    public Pig(String texturePath, float width, float height, Vector2 initialPosition, int health) {
        this.texturePath = texturePath;
        this.position = new Vector2(initialPosition);
        this.setHealth(health);
        this.bounds = new Rectangle(position.x, position.y, width, height);
        this.width = width;
        this.height = height;

        reloadSprite();
    }

    private void reloadSprite() {
        try {
            Texture texture = new Texture(texturePath);
            sprite = new Sprite(texture);
            sprite.setSize(width, height);
            sprite.setPosition(position.x, position.y);
        } catch (Exception e) {
            System.err.println("Failed to reload sprite for texture: " + texturePath);
            e.printStackTrace();
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void takeDamage(int damage) {
        setHealth(getHealth() - damage);
        System.out.println("Pig hit! Remaining health: " + getHealth());
        if (getHealth() < 0) setHealth(0);
    }

    public boolean isDestroyed() {
        return getHealth() <= 0;
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

    public void setHealth(int health) {
        this.health = health;
    }
}
