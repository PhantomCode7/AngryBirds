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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GameScreen extends State {
    private SpriteBatch batch;
    private static GameScreen instance;
    private Sprite background;
    private Sprite slingshot;
    private static List<Birds> birds;
    private int currentBirdIndex = 0;
    private static List<Pig> pigs;
    private static List<Materials> materials;

    private Vector2 birdInitialPosition;
    private Vector2 birdVelocity;
    private float gravity = -500f; // Gravity (pixels/second^2)

    private boolean isDragging = false;
    private boolean birdLaunched = false;
    private Vector2 dragStart = new Vector2();
    private Vector2 dragEnd = new Vector2();

    private Rectangle ground;

    private Sprite pauseButton;

    public void destroyAbove(Materials baseMaterial) {
        float baseY = baseMaterial.getBounds().y; // Y-coordinate of the destroyed block
        boolean flag = false;
        for (int i = 0; i < materials.size(); i++) {
            Materials material = materials.get(i);
            //boolean flag = false ;


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
        instance = this;
        initializePauseButton();
        initializeBackground();
        initializeGround();
        initializeSlingshot();
        initializeBirds();
        initializePigs();
        initializeMaterials();
    }

    private void initializePauseButton() {
        Texture pauseTexture = new Texture("pauseButton.png");
        pauseButton = new Sprite(pauseTexture);
        pauseButton.setSize(50, 50); // Adjust size as needed
        pauseButton.setPosition(750, 450); // Adjust position as needed (top-right corner)
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
        slingshot = new Sprite(new Texture("slingShot.png"));
        slingshot.setSize(50, 100);
        slingshot.setPosition(125 , 50);
    }

    private void initializeBirds() {
        birds = new ArrayList<>();

        // Initial position for all birds (at the slingshot)
        birdInitialPosition = new Vector2(slingshot.getX() + slingshot.getWidth() / 2 - 25, slingshot.getY() + slingshot.getHeight());

        // Add instances of your existing Birds classes
        birds.add(new Red(birdInitialPosition.cpy()));
        birdInitialPosition = new Vector2(100,50) ;
        birds.add(new TheBlues(birdInitialPosition.cpy(), 30));
        birdInitialPosition = new Vector2(80,50);
        birds.add(new Chuck(birdInitialPosition.cpy()));
        birdInitialPosition = new Vector2(60,50);
        birds.add(new Bomb(birdInitialPosition.cpy(), 50));
        birdInitialPosition = new Vector2(40,50) ;
        birds.add(new Terence(birdInitialPosition.cpy()));
    }


    private void initializePigs() {
        pigs = new ArrayList<>();


        pigs.add(new SmallPig(new Vector2(625, 50)));
        pigs.add(new MediumPig(new Vector2(625, 150)));
    }

    private void initializeMaterials() {
        materials = new ArrayList<>();


        materials.add(new Wooden("wood_vertical.png", 20 , 100 , new Vector2(600, 50)));
        materials.add(new Wooden("wood_vertical.png", 20 , 100 , new Vector2( 685, 50)));
        materials.add(new Wooden ("wood_horizontal.png" , 100 , 20 , new Vector2(600, 149)));
        materials.add(new Iron("iron_vertical.png", 20 , 100 , new Vector2(600, 168)));
        materials.add(new Iron("iron_vertical.png", 20 , 100 , new Vector2(685, 168)));
        materials.add(new Iron ("iron_horizontal.png" , 100 , 20 , new Vector2(600, 267)));
    }


    @Override
    public void input() {
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();

            Vector2 touch = new Vector2();
            touch.set(x, y);
            Main.viewport.unproject(touch);

            // Check if the pause button is clicked
            if (pauseButton.getBoundingRectangle().contains(touch.x, touch.y)) {
                manager.set(new Pause(manager, this)); // Pass the current GameScreen instance
                return; // Exit early to avoid processing further input
            }
        }
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

        if (birdLaunched && currentBirdIndex < birds.size()) {
            Birds currentBird = birds.get(currentBirdIndex);

            if (birdLaunched && Gdx.input.justTouched() && currentBirdIndex < birds.size()) {
                currentBird.activateAbility(); // Trigger the bird's special ability

            }

            // Apply gravity
            currentBird.getVelocity().y += gravity * delta;

            // Update bird's position based on velocity
            currentBird.updatePosition(delta);
            handleCollision(currentBird);
            if (currentBird instanceof TheBlues) {
                TheBlues bluesBird = (TheBlues) currentBird;

                // Update left bird
                if (bluesBird.getleftBird() != null) {
                    bluesBird.getleftBird().getVelocity().y += gravity * delta;
                    bluesBird.getleftBird().updatePosition(delta);
                    handleCollision(bluesBird.getleftBird());
                    // Remove the left bird if it is out of bounds
                    if (isOutOfBounds(bluesBird.getleftBird())) {
                        Birds a = bluesBird.getleftBird() ;
                        birds.remove(bluesBird.getleftBird());

                    }
                }

                // Update right bird
                if (bluesBird.getrightBird() != null) {
                    bluesBird.getrightBird().getVelocity().y += gravity * delta;
                    bluesBird.getrightBird().updatePosition(delta);
                    handleCollision(bluesBird.getrightBird());
                    // Remove the right bird if it is out of bounds
                    if (isOutOfBounds(bluesBird.getrightBird()) ) {
                         Birds a = bluesBird.getrightBird() ;
                        birds.remove(bluesBird.getrightBird());


                    }
                }
            }


            // Check if bird is out of bounds or hits the ground
            if (isOutOfBounds(currentBird)) {
                boolean flag = false ;
                if (currentBird instanceof  TheBlues){
                    TheBlues bluesBird = (TheBlues) currentBird;
                    if( bluesBird.getleftBird() != null && bluesBird.getrightBird() != null && !birds.contains(bluesBird.getleftBird()) && !birds.contains(bluesBird.getrightBird()) )
                    {
                        birdLaunched = false;
                        currentBirdIndex++;
                        flag = true ;
                    }
                    else if (bluesBird.getrightBird() == null && bluesBird.getleftBird() == null )
                    {
                        birdLaunched = false;
                        currentBirdIndex ++;
                        flag = true ;
                    }
                }
                else {
                    birdLaunched = false;
                    currentBirdIndex++;
                    flag = true ;
                }
                if (currentBirdIndex < birds.size() && flag) {
                    birds.get(currentBirdIndex).setPosition(
                        slingshot.getX() + slingshot.getWidth() / 2 - 25,
                        slingshot.getY() + slingshot.getHeight()
                    );
                }
            }
        }

        // Check win/lose conditions
        if (currentBirdIndex >= birds.size()) {
            if (pigs.isEmpty()) {
                Main.a++;
                manager.set(new WinningScreen(manager));

            } else {
                manager.set(new LosingScreen(manager));
            }
        } else if (pigs.isEmpty()) {
            Main.a++ ;
            manager.set(new WinningScreen(manager));
        }
    }

    private boolean isOutOfBounds(com.project.States.Birds.Birds birds) {
        return birds.getPosition().y <= ground.height ||
            birds.getPosition().x < 0 ||
            birds.getPosition().x > 800 ||
            birds.getPosition().y > 500 || birds.getPosition().y <= ground.height;
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


        for (int i = currentBirdIndex; i < birds.size(); i++) {
            birds.get(i).draw(batch);
        }

        // Draw pause button
        pauseButton.draw(batch);

        batch.end();
    }


    @Override
    public void dispose() {
        State currentState = manager.peek();
        if (currentState != null && !currentState.equals(this)) {
            batch.dispose();
            background.getTexture().dispose();
            slingshot.getTexture().dispose();
            pauseButton.getTexture().dispose();

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

    private void handleCollision(Birds bird) {
        // Check collisions with pigs
        for (int i = 0; i < pigs.size(); i++) {
            Pig pig = pigs.get(i);
            if (bird.getBounds().overlaps(pig.getBounds())) {
                pig.takeDamage(bird.getImpactDamage());

                if (pig.isDestroyed()) {
                    System.out.println("Pig destroyed!");
                    pigs.remove(i);
                    i--; // Adjust index after removal
                } else {
                    System.out.println("Pig hit but survived!");
                }

                // Bird dies after hitting a pig
                bird.setVelocity(new Vector2(0, 0)); // Stop the bird
                return; // Exit after handling collision
            }
        }

        // Check collisions with materials
        for (int i = 0; i < materials.size(); i++) {
            Materials material = materials.get(i);
            if (bird.getBounds().overlaps(material.getBounds())) {
                material.takeDamage(bird.getImpactDamage());

                if (material.isDestroyed()) {
                    System.out.println("Material destroyed!");
                    destroyAbove(material);
                    for (int k = 0 ; k<pigs.size() ; k++) {
                        if (pigs.get(k).getBounds().overlaps(material.getBounds())) {
                            pigs.remove(k);
                            k-- ;
                        }
                    }
                    materials.remove(i);
                    i--;
                }


                bird.fallBack();
                return;
            }
        }
    }


    public static List<com.project.States.Pigs.Pig> getPigs() {
        return pigs;
    }

    public static List<com.project.States.Materials.Materials> getMaterials() {
        return materials;
    }

    public static List<com.project.States.Birds.Birds> getBirds() {
        return birds;
    }

    public static GameScreen getInstance() {
        return instance;
    }
}
