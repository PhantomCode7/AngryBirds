package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;
import com.project.States.Screens.GameScreen;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class TheBlues extends Birds implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean hasSplit;
    private Birds leftBird;
    private Birds rightBird;
    private transient GameScreen gameScreen;

    public TheBlues(Vector2 initialPosition, int impactDamage, GameScreen gameScreen) {
        super("the_blues.png", 50, 50, initialPosition, impactDamage);
        this.hasSplit = false;
        this.gameScreen = gameScreen;
    }

    @Override
    protected void triggerAbility() {
        if (gameScreen == null) {
            throw new IllegalStateException("GameScreen not initialized in TheBlues!");
        }
        if (!hasSplit) {
            hasSplit = true;
            System.out.println("The Blues split into three!");

            // Calculate positions for the two new birds
            Vector2 leftPosition = position.cpy().add(-20, 20);  // Slightly to the left
            Vector2 rightPosition = position.cpy().add(20, 20); // Slightly to the right

            // Create new bird instances
            leftBird = new TheBlues(leftPosition, impactDamage / 2, gameScreen);  // Example of a smaller bird
            rightBird = new TheBlues(rightPosition, impactDamage / 2, gameScreen);

            // Set the same velocity as the parent bird
            leftBird.setVelocity(new Vector2(velocity.x, velocity.y));
            rightBird.setVelocity(new Vector2(velocity.x, velocity.y));

            // Add the new birds to the GameScreen birds list
            gameScreen.getBirds().add(leftBird);
            gameScreen.getBirds().add(rightBird);
        }
    }

    public Birds getleftBird() {
        return leftBird;
    }

    public Birds getrightBird() {
        return rightBird;
    }

    public void reloadSprite() {
        super.reloadSprite();
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        if (leftBird instanceof TheBlues) {
            ((TheBlues) leftBird).setGameScreen(gameScreen);
        }
        if (rightBird instanceof TheBlues) {
            ((TheBlues) rightBird).setGameScreen(gameScreen);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Deserialize non-transient fields
        // gameScreen will be set externally
    }
}
