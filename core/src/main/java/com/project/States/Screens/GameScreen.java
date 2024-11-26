package com.project.States.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.project.States.Birds.*;
import com.project.States.Main;
import com.project.States.Pigs.*;
import com.project.States.Materials.*;
import com.project.States.State;
import com.project.States.StateManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the main game screen where gameplay occurs.
 * Implements gravity, collision interactions, and bird launching.
 */
public class GameScreen extends State {
    private final SpriteBatch batch;

    private Sprite background;
    private Sprite slingshot;
    private List<Birds> birds; // List of all birds
    private int currentBirdIndex = 0; // Current bird to launch
    private List<Pig> pigs; // List of pigs
    private List<Materials> materials; // List of materials

    private Vector2 birdInitialPosition;

    private boolean isDragging = false;
    private boolean birdLaunched = false;
    private final Vector2 dragStart = new Vector2();
    private Vector2 dragEnd = new Vector2();

    private Rectangle ground; // Invisible platform

    public GameScreen(StateManager manager) {
        super(manager);
        batch = new SpriteBatch();

        initializeBackground();
        initializeGround();
        initializeSlingshot();
        initializeBirds();
        initializePigs();
        initializeMaterials();
    }

    /**
     * Initializes the game background.
     */
    private void initializeBackground() {
        // Use your existing background sprite
        background = new Sprite(new Texture("gameBackground.png"));
        background.setSize(800, 500); // Adjust to your game resolution
        background.setPosition(0, 0);
    }

    /**
     * Initializes the invisible ground platform.
     */
    private void initializeGround() {
        ground = new Rectangle(0, 0, 800, 10); // Invisible platform at the bottom
    }

    /**
     * Initializes the slingshot sprite.
     */
    private void initializeSlingshot() {
        slingshot = new Sprite(new Texture("slingshot.png"));
        slingshot.setSize(50, 100); // Adjust to your slingshot sprite size
        slingshot.setPosition(90, 90); // Position the slingshot on the screen
    }

    /**
     * Initializes all bird types.
     */
    private void initializeBirds() {
        birds = new ArrayList<>();

        // Initial position for all birds (at the slingshot)
        birdInitialPosition = new Vector2(slingshot.getX() + slingshot.getWidth() / 2 - 25, slingshot.getY() + slingshot.getHeight());

        // Add instances of your existing Birds classes
        birds.add(new RedBird(birdInitialPosition.cpy()));
        birds.add(new BlueBird(birdInitialPosition.cpy()));
        birds.add(new YellowBird(birdInitialPosition.cpy()));
        birds.add(new BlackBird(birdInitialPosition.cpy()));
        birds.add(new BigRed(birdInitialPosition.cpy()));
    }

    /**
     * Initializes all pigs in the level.
     */
    private void initializePigs() {
        pigs = new ArrayList<>();

        // Add instances of your existing Pig classes
        pigs.add(new SmallPig(new Vector2(600, 100))); // Adjust positions as needed
        pigs.add(new MediumPig(new Vector2(650, 100)));
    }

    /**
     * Initializes all materials in the level.
     */
    private void initializeMaterials() {
        materials = new ArrayList<>();

        // Add instances of your existing Materials classes
        materials.add(new Wooden("wood_horizontal.png", new Vector2(400, 100)));
        materials.add(new Wooden("wood_vertical.png", new Vector2(400, 120)));

        materials.add(new Iron("iron_horizontal.png", new Vector2(500, 100)));
        materials.add(new Iron("iron_vertical.png", new Vector2(500, 120)));
    }

    /**
     * Handles user input for dragging and launching the bird.
     */
    @Override
    public void input() {
        if (!birdLaunched && currentBirdIndex < birds.size()) {
            Birds currentBird = birds.get(currentBirdIndex);

            if (Gdx.input.isTouched()) {
                if (!isDragging) {
                    // Record the starting drag position
                    dragStart.set(Gdx.input.getX(), Gdx.input.getY());
                    Main.viewport.unproject(dragStart);

                    // Check if the drag starts on the current bird
                    if (currentBird.getBounds().contains(dragStart)) {
                        isDragging = true;
                    }
                } else {
                    // Update the drag end position
                    dragEnd.set(Gdx.input.getX(), Gdx.input.getY());
                    Main.viewport.unproject(dragEnd);

                    // Calculate the new position within a maximum drag distance
                    float maxDragDistance = 50f; // Maximum distance the bird can be dragged
                    Vector2 dragVector = dragEnd.cpy().sub(birdInitialPosition);
                    if (dragVector.len() > maxDragDistance) {
                        dragVector.nor().scl(maxDragDistance);
                        dragEnd = birdInitialPosition.cpy().add(dragVector);
                    }

                    // Move the bird to follow the drag
                    currentBird.setPosition(dragEnd.x - currentBird.getWidth() / 2, dragEnd.y - currentBird.getHeight() / 2);
                }
            } else {
                if (isDragging) {
                    // Launch the bird
                    isDragging = false;

                    // Calculate launch velocity: opposite direction of the drag
                    Vector2 launchVelocity = birdInitialPosition.cpy().sub(dragEnd).scl(13f); // Adjust scaling factor as needed
                    currentBird.setVelocity(launchVelocity);
                    birdLaunched = true;
                }
            }
        }

        // Handle special ability trigger mid-flight
        if (birdLaunched && currentBirdIndex < birds.size()) {
            Birds currentBird = birds.get(currentBirdIndex);

            // Trigger ability when user taps (or specify your condition, e.g., double-tap or key press)
            if (Gdx.input.justTouched()) {
                currentBird.applySpecialAbility(); // Call the bird's special ability
            }
        }
    }

    /**
     * Updates game logic, including applying gravity and handling collisions.
     * @param delta Time elapsed since last frame.
     */
    @Override
    public void update(float delta) {
        input();

        if (birdLaunched && currentBirdIndex < birds.size()) {
            Birds currentBird = birds.get(currentBirdIndex);

            // Apply gravity (pixels/second^2)
            float gravity = -500f;
            currentBird.getVelocity().y += gravity * delta;

            // Update bird's position based on velocity
            currentBird.updatePosition(delta);

            // Check collision with ground
            if (currentBird.getPosition().y <= ground.height) {
                currentBird.setPosition(currentBird.getPosition().x, ground.height);
                currentBird.setVelocity(new Vector2(0, 0)); // Corrected to pass Vector2
                birdLaunched = false;
                currentBirdIndex++; // Move to next bird
            }

            // Check collision with pigs
            for (int i = 0; i < pigs.size(); i++) {
                Pig pig = pigs.get(i);
                if (currentBird.getBounds().overlaps(pig.getBounds())) {
                    pigs.remove(i);
                    i--;

                    // Check if all pigs are destroyed
                    if (pigs.isEmpty()) {
                        manager.set(new WinningScreen(manager));
                        return;
                    }
                }
            }

            // Check collision with materials
            // Check collision with materials
            for (int i = 0; i < materials.size(); i++) {
                Materials material = materials.get(i);

                if (currentBird.getBounds().overlaps(material.getBounds())) {
                    // Apply damage to the material
                    material.takeDamage(50); // Bird deals 50 damage

                    // Stop the bird's movement
                    currentBird.setVelocity(new Vector2(0, 0));

                    // Position the bird at the point of contact
                    currentBird.setPosition(
                        currentBird.getPosition().x,
                        Math.max(currentBird.getPosition().y, material.getBounds().y + material.getBounds().height)
                    );

                    // Remove material if it is destroyed
                    if (material.isDestroyed()) {
                        materials.remove(i);
                    }

                    // Stop checking for further collisions for this frame
                    break;
                }
            }


            // Check if bird goes off-screen
            if (currentBird.getPosition().x < 0 || currentBird.getPosition().x > 800 ||
                currentBird.getPosition().y < 0 || currentBird.getPosition().y > 500) {
                birdLaunched = false;
                currentBirdIndex++; // Move to next bird
            }
        }

        // Check for game over or win conditions
        if (currentBirdIndex >= birds.size()) {
            if (pigs.isEmpty()) {
                manager.set(new WinningScreen(manager));
            } else {
                manager.set(new LosingScreen(manager));
            }
        } else if (pigs.isEmpty()) {
            manager.set(new WinningScreen(manager));
        }
    }

    /**
     * Renders all game elements on the screen.
     * @param sb SpriteBatch used for drawing.
     */
    @Override
    public void render(SpriteBatch sb) {
        batch.setProjectionMatrix(Main.viewport.getCamera().combined);
        batch.begin();

        // Draw background
        background.draw(batch);

        // Draw slingshot
        slingshot.draw(batch);

        // Draw materials
        for (Materials material : materials) {
            material.draw(batch);
        }

        // Draw pigs
        for (Pig pig : pigs) {
            pig.draw(batch);
        }

        // Draw birds (only those not yet launched)
        for (int i = currentBirdIndex; i < birds.size(); i++) {
            birds.get(i).draw(batch);
        }

        batch.end();
    }

    /**
     * Disposes of all resources to prevent memory leaks.
     */
    @Override
    public void dispose() {
        batch.dispose();
        background.getTexture().dispose();
        slingshot.getTexture().dispose();

        for (Birds bird : birds) {
            bird.dispose();
        }
        for (Pig pig : pigs) {
            pig.dispose();
        }
        for (Materials material : materials) {
            material.dispose();
        }
    }
}
