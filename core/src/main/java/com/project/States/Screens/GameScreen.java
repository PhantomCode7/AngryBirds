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

public class GameScreen extends State {
    private SpriteBatch batch;

    private Sprite background;
    private Sprite slingshot;
    private List<Birds> birds;
    private int currentBirdIndex = 0;
    private List<Pig> pigs;
    private List<Materials> materials;

    private Vector2 birdInitialPosition;
    private Vector2 birdVelocity;
    private float gravity = -500f; // Gravity (pixels/second^2)

    private boolean isDragging = false;
    private boolean birdLaunched = false;
    private Vector2 dragStart = new Vector2();
    private Vector2 dragEnd = new Vector2();

    private Rectangle ground;

    private void destroyAbove(Materials baseMaterial) {
        float baseY = baseMaterial.getBounds().y; // Y-coordinate of the destroyed block
        boolean flag = false;
        for (int i = 0; i < materials.size(); i++) {
            Materials material = materials.get(i);
            //boolean flag = false ;

            // Check if the block is directly above the destroyed block
            if (materials.get(i) != baseMaterial && material.getBounds().overlaps(baseMaterial.getBounds()) && material.getBounds().y >= baseY) {
                flag = true;
                destroyAbove(material);
                for (int j = 0 ; j<pigs.size() ; j++) {
                    Pig pig = pigs.get(j);
                    if (pig.getBounds().overlaps(material.getBounds()))
                    {
                        pigs.remove(j);
                        j--;
                    }
                }
                materials.remove(i);
                i--;
            }
        }


    }


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

    private void initializeBackground() {

        background = new Sprite(new Texture("gameBackground.png"));
        background.setSize(800, 500);
        background.setPosition(0, 0);
    }

    private void initializeGround() {
        ground = new Rectangle(0, 0, 800, 10);
    }

    private void initializeSlingshot() {
        slingshot = new Sprite(new Texture("slingshot.png"));
        slingshot.setSize(50, 100);
        slingshot.setPosition(125 , 50);
    }

    private void initializeBirds() {
        birds = new ArrayList<>();

        // Initial position for all birds (at the slingshot)
        birdInitialPosition = new Vector2(slingshot.getX() + slingshot.getWidth() / 2 - 25, slingshot.getY() + slingshot.getHeight());

        // Add instances of your existing Birds classes
        birds.add(new RedBird(birdInitialPosition.cpy()));
        birdInitialPosition = new Vector2(100,50) ;
        birds.add(new BlueBird(birdInitialPosition.cpy()));
        birdInitialPosition = new Vector2(80,50);
        birds.add(new YellowBird(birdInitialPosition.cpy()));
        birdInitialPosition = new Vector2(60,50);
        birds.add(new BlackBird(birdInitialPosition.cpy()));
        birdInitialPosition = new Vector2(40,50) ;
        birds.add(new BigRed(birdInitialPosition.cpy()));
    }


    private void initializePigs() {
        pigs = new ArrayList<>();


        pigs.add(new SmallPig(new Vector2(625, 50))); // Adjust positions as needed
        pigs.add(new MediumPig(new Vector2(625, 150)));
    }

    private void initializeMaterials() {
        materials = new ArrayList<>();

        // Add instances of your existing Materials classes
        materials.add(new Wooden("wood_vertical.png", 20 , 100 , new Vector2(600, 50)));
        materials.add(new Wooden("wood_vertical.png", 20 , 100 , new Vector2( 685, 50)));
        materials.add(new Wooden ("wood_horizontal.png" , 100 , 20 , new Vector2(600, 149)));
        materials.add(new Iron("iron_vertical.png", 20 , 100 , new Vector2(600, 168)));
        materials.add(new Iron("iron_vertical.png", 20 , 100 , new Vector2(685, 168)));
        materials.add(new Iron ("iron_horizontal.png" , 100 , 20 , new Vector2(600, 267)));
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
                    Vector2 dragVector = dragEnd.cpy().sub(slingshot.getX(), slingshot.getY());
                    if (dragVector.len() > maxDragDistance) {
                        dragVector.nor().scl(maxDragDistance);
                        Vector2 v = new Vector2 (slingshot.getX() + slingshot.getWidth() / 2 - 25, slingshot.getY() + slingshot.getHeight()) ;
                        dragEnd = v.cpy().add(dragVector);

                    }

                    // Move the bird to follow the drag
                    currentBird.setPosition(dragEnd.x - currentBird.getWidth() / 2, dragEnd.y - currentBird.getHeight() / 2);
                    //currentBirdIndex++;
                    //birds.get(currentBirdIndex).setPosition(  slingshot.getX() + slingshot.getWidth() / 2 - 25, slingshot.getY() + slingshot.getHeight());
                }
            } else {
                if (isDragging) {
                    // Launch the bird
                    isDragging = false;

                    // Calculate launch velocity: opposite direction of the drag
                    Vector2 v = new Vector2 (slingshot.getX() + slingshot.getWidth() / 2 - 25, slingshot.getY() + slingshot.getHeight()) ;
                    Vector2 launchVelocity = v.cpy().sub(dragEnd).scl(13f); // Adjust scaling factor as needed
                    currentBird.setVelocity(launchVelocity);
                    birdLaunched = true;
                }
            }
        }
    }


    @Override
    public void update(float delta) {
        input();
        boolean flag = false ;

        if ( !flag && birdLaunched && currentBirdIndex < birds.size() ) {
            Birds currentBird = birds.get(currentBirdIndex);

            // Apply gravity
            currentBird.getVelocity().y += gravity * delta;

            // Update bird's position based on velocity
            currentBird.updatePosition(delta);

            // Check collision with ground
            if (currentBird.getPosition().y <= ground.height) {
                currentBirdIndex++;
                currentBird.setPosition(currentBird.getPosition().x, ground.height);
                currentBird.setVelocity(new Vector2(0, 0));
                birdLaunched = false;
                //currentBirdIndex++;
                if (currentBirdIndex < birds.size()) {
                    birds.get(currentBirdIndex).setPosition(slingshot.getX() + slingshot.getWidth() / 2 - 25, slingshot.getY() + slingshot.getHeight());
                }
                flag = true; // Move to next bird
            }


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

            for (int i = 0; i < materials.size(); i++) {
                Materials material = materials.get(i);

                if (currentBird.getBounds().overlaps(material.getBounds())) {
                    // Apply damage to the material
                    material.takeDamage(50);
                    currentBird.setVelocity(new Vector2(0, 0)); // Stop the bird's velocity

                    if (material.isDestroyed()) {
                        destroyAbove(material);
                        for(int k = 0 ; k<pigs.size() ; k++) {
                            Pig pig = pigs.get(k);
                            if (pig.getBounds().overlaps(material.getBounds())) {
                                pigs.remove(k);
                                k-- ;
                            }
                        }
                        materials.remove(i);
                        i--; // Adjust index after removal
                    }
                    break;
                }
            }


            if ( (!flag ) && (currentBird.getPosition().x < 0 || currentBird.getPosition().x > 800 ||
                currentBird.getPosition().y < 0 || currentBird.getPosition().y > 500)) {
                birdLaunched = false;
                currentBirdIndex ++ ;
                if (currentBirdIndex < birds.size()) {
                    //currentBirdIndex++;
                    birds.get(currentBirdIndex).setPosition(slingshot.getX() + slingshot.getWidth() / 2 - 25, slingshot.getY() + slingshot.getHeight());
                    flag = true ;
                }
            }
        }


        if (currentBirdIndex >= birds.size() ) {
            if (pigs.isEmpty()) {
                manager.set(new WinningScreen(manager));
            } else {
                manager.set(new LosingScreen(manager));
            }
        } else if (pigs.isEmpty()) {
            manager.set(new WinningScreen(manager));
        }
    }


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
