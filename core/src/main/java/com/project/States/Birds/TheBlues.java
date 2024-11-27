package com.project.States.Birds;

import com.badlogic.gdx.math.Vector2;
import com.project.States.Screens.GameScreen;

public class TheBlues extends Birds {
    private boolean hasSplit;
    Birds leftBird;
    Birds rightBird;

    public TheBlues(Vector2 initialPosition, int impactDamage) {
        super("the_blues.png", 50, 50, initialPosition, impactDamage);
        this.hasSplit = false;
    }

    @Override
    protected void triggerAbility() {
        if (!hasSplit) {
            hasSplit = true;
            System.out.println("The Blues split into three!");

            // Calculate positions for the two new birds
            Vector2 leftPosition = position.cpy().add(-20, 20);  // Slightly to the left
            Vector2 rightPosition = position.cpy().add(20, 20); // Slightly to the right

            // Create new bird instances
            leftBird = new TheBlues(leftPosition, impactDamage / 2);  // Example of a smaller bird
            rightBird = new TheBlues(rightPosition, impactDamage / 2);

            // Set the same velocity as the parent bird
            leftBird.setVelocity(new Vector2(velocity.x, velocity.y));
            rightBird.setVelocity(new Vector2(velocity.x, velocity.y));

            // Add the new birds to the GameScreen birds list
          GameScreen.getBirds().add(leftBird);
           GameScreen.getBirds().add(rightBird);

        }
    }
    public Birds getleftBird(){
        return leftBird;
    }
    public Birds getrightBird(){
        return rightBird;
    }
}
