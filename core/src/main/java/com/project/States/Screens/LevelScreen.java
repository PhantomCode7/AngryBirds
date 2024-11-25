package com.project.States.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.project.States.State;
import com.project.States.StateManager;
import com.project.States.Main;

import java.util.ArrayList;
import java.util.List;

public class LevelScreen extends State {
    private SpriteBatch batch;
    private BitmapFont font;
    private GlyphLayout layout;
    private Texture backTexture;
    private Sprite backButton;
    private Texture nextTexture;
    private Sprite nextButton;
    private Texture prevTexture;
    private Sprite prevButton;
    private List<Sprite> levelButtons;
    private List<Integer> levelNumbers;
    private int currentPage;
    private final int levelsPerPage = 10; // Number of levels per page
    public static int selectedLevel; // Static variable to store selected level

    public LevelScreen(StateManager manager) {
        super(manager);
        batch = new SpriteBatch();
        font = new BitmapFont(); // Default font
        layout = new GlyphLayout();
        currentPage = 1;
        levelButtons = new ArrayList<>();
        levelNumbers = new ArrayList<>();
        createButtonTextures();
        populateLevels();
    }

    /**
     * Creates textures and sprites for navigation buttons.
     */
    private void createButtonTextures() {
        // Create Back Button (positioned at top left, colored gray)
        Pixmap pixmapBack = new Pixmap(100, 50, Pixmap.Format.RGBA8888);
        pixmapBack.setColor(Color.GRAY);
        pixmapBack.fill();
        backTexture = new Texture(pixmapBack);
        backButton = new Sprite(backTexture);
        backButton.setPosition(50, 400); // Top left corner
        pixmapBack.dispose();

        // Create Previous Button (positioned at bottom left, colored silver)
        Pixmap pixmapPrev = new Pixmap(200, 50, Pixmap.Format.RGBA8888);
        pixmapPrev.setColor(new Color(0.75f, 0.75f, 0.75f, 1f)); // Silver color
        pixmapPrev.fill();
        prevTexture = new Texture(pixmapPrev);
        prevButton = new Sprite(prevTexture);
        prevButton.setPosition(50, 50); // Bottom left corner
        pixmapPrev.dispose();

        // Create Next Button (positioned at bottom right, colored golden)
        Pixmap pixmapNext = new Pixmap(200, 50, Pixmap.Format.RGBA8888);
        pixmapNext.setColor(new Color(1f, 0.843f, 0f, 1f)); // Golden color (RGB: 255, 215, 0)
        pixmapNext.fill();
        nextTexture = new Texture(pixmapNext);
        nextButton = new Sprite(nextTexture);
        nextButton.setPosition(650, 50); // Bottom right corner
        pixmapNext.dispose();
    }

    /**
     * Populates level buttons based on the current page.
     */
    private void populateLevels() {
        int startLevel = (currentPage - 1) * levelsPerPage + 1;
        levelButtons.clear();
        levelNumbers.clear();
        for (int i = startLevel; i < startLevel + levelsPerPage; i++) {
            Pixmap pixmapLevel = new Pixmap(80, 80, Pixmap.Format.RGBA8888);
            if (i % 2 == 0) {
                pixmapLevel.setColor(Color.ORANGE);
            } else {
                pixmapLevel.setColor(Color.CYAN);
            }
            pixmapLevel.fill();
            Texture levelTexture = new Texture(pixmapLevel);
            Sprite levelButton = new Sprite(levelTexture);
            int col = (i - startLevel) % 5; // 5 columns
            int row = (i - startLevel) / 5; // 2 rows (since levelsPerPage=10)
            levelButton.setPosition(50 + col * 120, 300 - row * 120); // Adjusted positions
            levelButtons.add(levelButton);
            levelNumbers.add(i);
            pixmapLevel.dispose();
        }
    }

    @Override
    public void input() {
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            Vector2 touch = new Vector2(x, y);
            Main.viewport.unproject(touch);

            // Check Back Button
            if (backButton.getBoundingRectangle().contains(touch.x, touch.y)) {
                manager.set(new MainScreen(manager));
            }

            // Check Next Button
            if (nextButton.getBoundingRectangle().contains(touch.x, touch.y)) {
                currentPage++;
                populateLevels();
            }

            // Check Previous Button
            if (prevButton.getBoundingRectangle().contains(touch.x, touch.y) && currentPage > 1) {
                currentPage--;
                populateLevels();
            }

            // Check Level Buttons
            for (int i = 0; i < levelButtons.size(); i++) {
                if (levelButtons.get(i).getBoundingRectangle().contains(touch.x, touch.y)) {
                    selectedLevel = levelNumbers.get(i);
                    manager.set(new GameScreen(manager));
                    break; // Prevent multiple selections
                }
            }
        }
    }

    @Override
    public void update(float delta) {
        input();
    }

    @Override
    public void render(SpriteBatch sb) {
        batch.begin();

        // Draw Navigation Buttons
        backButton.draw(batch);
        if (currentPage > 1) {
            prevButton.draw(batch);
        }
        nextButton.draw(batch); // Ensure this is drawn last among navigation buttons

        // Draw Button Labels (Updated for better alignment)
        // Back Button Label
        layout.setText(font, "Back");
        font.draw(batch, layout,
            backButton.getX() + (backButton.getWidth() - layout.width) / 2,
            backButton.getY() + (backButton.getHeight() + layout.height) / 2 - 5);

        // Next Button Label
        layout.setText(font, "Next");
        font.draw(batch, layout,
            nextButton.getX() + (nextButton.getWidth() - layout.width) / 2,
            nextButton.getY() + (nextButton.getHeight() + layout.height) / 2 - 5);

        // Previous Button Label
        if (currentPage > 1) {
            layout.setText(font, "Previous");
            font.draw(batch, layout,
                prevButton.getX() + (prevButton.getWidth() - layout.width) / 2,
                prevButton.getY() + (prevButton.getHeight() + layout.height) / 2 - 5);
        }

        // Draw Level Buttons and Labels
        for (int i = 0; i < levelButtons.size(); i++) {
            levelButtons.get(i).draw(batch);
            layout.setText(font, "Level " + levelNumbers.get(i));
            font.draw(batch, layout,
                levelButtons.get(i).getX() + (levelButtons.get(i).getWidth() - layout.width) / 2,
                levelButtons.get(i).getY() + (levelButtons.get(i).getHeight() + layout.height) / 2 - 5);
        }

        // Draw Page Number
        layout.setText(font, "Page " + currentPage);
        font.draw(batch, layout, 400 - layout.width / 2, 30);

        batch.end();
    }


    @Override
    public void dispose() {
        backTexture.dispose();
        nextTexture.dispose();
        prevTexture.dispose();
        for (Sprite s : levelButtons) {
            // Dispose only unique textures, not reused ones
            if (s.getTexture() != null) {
                s.getTexture().dispose();
            }
        }
        font.dispose();
        batch.dispose();
    }

}
