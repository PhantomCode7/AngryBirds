package com.project.States.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.project.States.MusicManager;
import com.project.States.Main;
import com.project.States.State;
import com.project.States.StateManager;

public class SettingsScreen extends State {
    private Texture background;
    private Texture muteButtonTexture;
    private Texture unmuteButtonTexture;
    private Texture backButtonTexture;
    private Sprite backButton;
    private Sprite muteButton;

    public SettingsScreen(StateManager manager) {
        super(manager);
        background = new Texture("settings_background.png");
        muteButtonTexture = new Texture("mute.png");
        unmuteButtonTexture = new Texture("unmute.png");
        backButtonTexture = new Texture("back.png");

        backButton = new Sprite(backButtonTexture);
        backButton.setPosition(50, 400);
        backButton.setSize(100, 50);

        updateMuteButton();
    }

    private void updateMuteButton() {
        if (MusicManager.getInstance().isMuted()) {
            muteButton = new Sprite(unmuteButtonTexture);
        } else {
            muteButton = new Sprite(muteButtonTexture);
        }
        muteButton.setPosition(350, 250);
        muteButton.setSize(100, 100);
    }

    @Override
    public void input() {
        if (Gdx.input.justTouched()) {
            Vector2 touch = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            Main.viewport.unproject(touch);

            if (muteButton.getBoundingRectangle().contains(touch.x, touch.y)) {
                MusicManager.getInstance().toggleMute();
                updateMuteButton();
            } else if (backButton.getBoundingRectangle().contains(touch.x, touch.y)) {
                manager.pop();
            }
        }
    }

    @Override
    public void update(float delta) {
        input();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(background, 0, 0, Main.viewport.getWorldWidth(), Main.viewport.getWorldHeight());
        muteButton.draw(batch);
        backButton.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        muteButtonTexture.dispose();
        unmuteButtonTexture.dispose();
        backButtonTexture.dispose();
    }
}
