package com.project.States.Tests;

import com.badlogic.gdx.math.Vector2;
import com.project.States.Birds.Birds;
import com.project.States.Materials.Materials;
import com.project.States.Pigs.Pig;
import com.project.States.StateManager;
import com.project.States.Screens.GameScreen;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GameScreenTest {

    private GameScreen gameScreen;
    private StateManager stateManager;

    @Before
    public void setUp() {
        // Create a real StateManager instance
        stateManager = new StateManager();

        // Initialize GameScreen
        gameScreen = new GameScreen(stateManager);

        // Ensure transient fields are initialized
        gameScreen.reloadTransientFields();
    }

    @Test
    public void testInitializeBirds() {
        List<Birds> birds = gameScreen.getBirds();
        assertNotNull("Birds list should not be null.", birds);
        assertEquals("There should be 5 birds initialized.", 5, birds.size());
    }

    @Test
    public void testInitializePigs() {
        List<Pig> pigs = gameScreen.getPigs();
        assertNotNull("Pigs list should not be null.", pigs);
        assertFalse("Pigs list should not be empty.", pigs.isEmpty());
    }

    @Test
    public void testInitializeMaterials() {
        List<Materials> materials = gameScreen.getMaterials();
        assertNotNull("Materials list should not be null.", materials);
        assertFalse("Materials list should not be empty.", materials.isEmpty());
    }

    @Test
    public void testDestroyAbove() {
        Materials baseMaterial = gameScreen.getMaterials().get(0); // Get the first material
        int initialMaterialCount = gameScreen.getMaterials().size();
        gameScreen.destroyAbove(baseMaterial);

        assertTrue("Materials should be destroyed above the base.", gameScreen.getMaterials().size() < initialMaterialCount);
    }

    @Test
    public void testInputLaunchBird() {
        Birds currentBird = gameScreen.getBirds().get(0);
        Vector2 initialPosition = currentBird.getPosition().cpy();

        // Simulate input to launch the bird
        gameScreen.input();
        gameScreen.update(1.0f); // Simulate 1 second of update

        assertNotEquals("Bird should have moved after being launched.", initialPosition, currentBird.getPosition());
    }

    @Test
    public void testHandleCollisionWithPig() {
        Birds bird = gameScreen.getBirds().get(0);
        bird.setPosition(gameScreen.getPigs().get(0).getBounds().getX(), gameScreen.getPigs().get(0).getBounds().getY());

        int initialPigCount = gameScreen.getPigs().size();
        gameScreen.update(1.0f); // Trigger collision handling

        assertTrue("Pig should be removed after collision with a bird.", gameScreen.getPigs().size() < initialPigCount);
    }

    @Test
    public void testHandleCollisionWithMaterial() {
        Birds bird = gameScreen.getBirds().get(0);
        bird.setPosition(gameScreen.getMaterials().get(0).getBounds().getX(), gameScreen.getMaterials().get(0).getBounds().getY());

        int initialMaterialCount = gameScreen.getMaterials().size();
        gameScreen.update(1.0f); // Trigger collision handling

        assertTrue("Material should be destroyed after collision with a bird.", gameScreen.getMaterials().size() < initialMaterialCount);
    }

    @Test
    public void testOutOfBoundsBird() {
        Birds bird = gameScreen.getBirds().get(0);
        bird.setPosition(-10, -10); // Set bird out of bounds

        gameScreen.update(1.0f);

        assertFalse("Bird should be removed if out of bounds.", gameScreen.getBirds().contains(bird));
    }

    @Test
    public void testSaveGame() {
        try {
            gameScreen.saveGame();
        } catch (Exception e) {
            fail("Saving the game should not throw any exceptions.");
        }
    }

    @Test
    public void testLoadGame() {
        // Save the game first
        gameScreen.saveGame();

        // Load the game
        GameScreen loadedGame = GameScreen.loadGame(stateManager);

        assertNotNull("Loaded game should not be null.", loadedGame);
        assertEquals("Loaded game should have the same number of birds.", gameScreen.getBirds().size(), loadedGame.getBirds().size());
        assertEquals("Loaded game should have the same number of pigs.", gameScreen.getPigs().size(), loadedGame.getPigs().size());
    }
}
