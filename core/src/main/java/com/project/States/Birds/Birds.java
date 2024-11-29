package com.project.States.Birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;

public abstract class Birds implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    protected transient Sprite sprite;
    protected Vector2 position;
    protected Vector2 velocity;
    protected Rectangle bounds;
    protected int impactDamage;
    protected boolean abilityUsed;
    protected String texturePath;


    public Birds(String texturePath, float width, float height, Vector2 initialPosition, int impactDamage) {
        this.texturePath = texturePath;
        Texture texture = new Texture(texturePath);
        sprite = new Sprite(texture);
        sprite.setSize(width, height);
        position = new Vector2(initialPosition);
        sprite.setPosition(position.x, position.y);
        bounds = new Rectangle(position.x, position.y, width, height);
        velocity = new Vector2(0, 0);
        this.impactDamage = impactDamage;
    }

    public void reloadSprite() {
        Texture texture = new Texture(texturePath);
        sprite = new Sprite(texture);
        sprite.setSize(bounds.width, bounds.height);
        sprite.setPosition(position.x, position.y);
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        reloadSprite();
    }

    public int getImpactDamage() {
        return impactDamage;
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

    public float getWidth() {
        return sprite.getWidth();
    }

    public float getHeight() {
        return sprite.getHeight();
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

    public void activateAbility() {
        if (!abilityUsed) {
            abilityUsed = true;
            triggerAbility();
        }
    }

    protected  void triggerAbility(){
        System.out.println("This bird has no special ability!");
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }

    public void fallBack() {
        velocity.set(-velocity.x * 0.2f, velocity.y * 0.3f);
        position.add(velocity.x * 0.1f, velocity.y * 0.1f);
    }
}
