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
    protected int impactDamage;
    protected boolean abilityUsed;

    public Birds(String texturePath, float width, float height, Vector2 initialPosition, int impactDamage) {
        Texture texture = new Texture(texturePath);
        sprite = new Sprite(texture);
        sprite.setSize(width, height);
        position = new Vector2(initialPosition);
        sprite.setPosition(position.x, position.y);
        bounds = new Rectangle(position.x, position.y, width, height);
        velocity = new Vector2(0, 0);
        this.impactDamage = impactDamage;
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
            abilityUsed = true; // Mark ability as used
            triggerAbility();   // Delegate to subclass-specific ability logic
        }
    }

    protected  void triggerAbility(){
        // Default ability does nothing
        System.out.println("This bird has no special ability!");
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }

    public void fallBack() {
        velocity.set(-velocity.x * 0.2f, velocity.y * 0.3f); // Reverse and reduce speed
        position.add(velocity.x * 0.1f, velocity.y * 0.1f); // Apply fallback immediately
        sprite.setPosition(position.x, position.y); // Update sprite position
        bounds.setPosition(position.x, position.y); // Update bounds
    }
}
