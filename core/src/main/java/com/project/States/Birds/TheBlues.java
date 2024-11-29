package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;
import com.project.States.Screens.GameScreen;
import com.project.States.Screens.Level2;
import com.project.States.Screens.Level3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class TheBlues extends Birds implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean hasSplit;
    private Birds leftBird;
    private Birds rightBird;
    private transient GameScreen gameScreen;
    private transient Level2 level2;
    private transient Level3 level3;

    public TheBlues(Vector2 initialPosition, int impactDamage, GameScreen gameScreen) {
        super("the_blues.png", 50, 50, initialPosition, impactDamage);
        this.hasSplit = false;
        this.gameScreen = gameScreen;
    }

    public TheBlues(Vector2 initialPosition, int impactDamage, Level2 gameScreen) {
        super("the_blues.png", 50, 50, initialPosition, impactDamage);
        this.hasSplit = false;
        this.level2 = gameScreen;
    }

    public TheBlues(Vector2 initialPosition, int impactDamage, Level3 gameScreen) {
        super("the_blues.png", 50, 50, initialPosition, impactDamage);
        this.hasSplit = false;
        this.level3 = gameScreen;
    }

    @Override
    protected void triggerAbility() {
        if (gameScreen == null) {
            throw new IllegalStateException("GameScreen not initialized in TheBlues!");
        }
        if (!hasSplit) {
            hasSplit = true;
            System.out.println("The Blues split into three!");

            Vector2 leftPosition = position.cpy().add(-20, 20);
            Vector2 rightPosition = position.cpy().add(20, 20);

            leftBird = new TheBlues(leftPosition, impactDamage / 2, gameScreen);
            rightBird = new TheBlues(rightPosition, impactDamage / 2, gameScreen);

            leftBird.setVelocity(new Vector2(velocity.x, velocity.y));
            rightBird.setVelocity(new Vector2(velocity.x, velocity.y));

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

    public void setGameScreen(Level2 gameScreen) {
        this.level2 = gameScreen;
        if (leftBird instanceof TheBlues) {
            ((TheBlues) leftBird).setGameScreen(gameScreen);
        }
        if (rightBird instanceof TheBlues) {
            ((TheBlues) rightBird).setGameScreen(gameScreen);
        }
    }

    public void setGameScreen(Level3 gameScreen) {
        this.level3 = gameScreen;
        if (leftBird instanceof TheBlues) {
            ((TheBlues) leftBird).setGameScreen(gameScreen);
        }
        if (rightBird instanceof TheBlues) {
            ((TheBlues) rightBird).setGameScreen(gameScreen);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
