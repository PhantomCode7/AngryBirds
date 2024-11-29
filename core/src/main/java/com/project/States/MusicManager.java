package com.project.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicManager {

    private static MusicManager instance;
    private Music backgroundMusic;
    private boolean isMuted = false;

    private MusicManager() {
    }

    public static MusicManager getInstance() {
        if (instance == null) {
            instance = new MusicManager();
            instance.init();
        }
        return instance;
    }

    private void init() {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background_music.mp3"));
        backgroundMusic.setLooping(true);
    }

    public void playMusic() {
        if (!backgroundMusic.isPlaying()) {
            backgroundMusic.play();
        }
    }

    public void stopMusic() {
        if (backgroundMusic.isPlaying()) {
            backgroundMusic.stop();
        }
    }

    public void pauseMusic() {
        if (backgroundMusic.isPlaying()) {
            backgroundMusic.pause();
        }
    }

    public void resumeMusic() {
        if (!backgroundMusic.isPlaying()) {
            backgroundMusic.play();
        }
    }

    public void dispose() {
        backgroundMusic.dispose();
    }

    public void setVolume(float volume) {
        backgroundMusic.setVolume(volume);
    }

    public void toggleMute() {
        if (isMuted) {
            backgroundMusic.setVolume(1.0f);
            isMuted = false;
        } else {
            backgroundMusic.setVolume(0.0f);
            isMuted = true;
        }
    }

    public boolean isMuted() {
        return isMuted;
    }
}
