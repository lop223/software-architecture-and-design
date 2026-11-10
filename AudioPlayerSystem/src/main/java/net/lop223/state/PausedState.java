package net.lop223.state;

import net.lop223.SystemMediaPlayerEngine;
import net.lop223.utils.PlaybackState;

public final class PausedState implements MediaPlayerState {
    public static final PausedState INSTANCE = new PausedState();

    private PausedState() {}

    @Override
    public PlaybackState getStatus() {
        return PlaybackState.PAUSED;
    }

    @Override
    public void play(SystemMediaPlayerEngine engine) {
        engine.startPlayback();
    }

    @Override
    public void pause(SystemMediaPlayerEngine engine) {
        // Плеєр уже на паузі.
    }

    @Override
    public void stop(SystemMediaPlayerEngine engine) {
        engine.stopPlayback();
    }
}