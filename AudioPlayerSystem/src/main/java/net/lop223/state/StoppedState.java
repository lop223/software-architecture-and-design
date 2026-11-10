package net.lop223.state;

import net.lop223.SystemMediaPlayerEngine;
import net.lop223.utils.PlaybackState;

public final class StoppedState implements MediaPlayerState {
    public static final StoppedState INSTANCE = new StoppedState();

    private StoppedState() {}

    @Override
    public PlaybackState getStatus() {
        return PlaybackState.STOPPED;
    }

    @Override
    public void play(SystemMediaPlayerEngine engine) {
        engine.startPlayback();
    }

    @Override
    public void pause(SystemMediaPlayerEngine engine) {
        // У зупиненому стані пауза нічого не змінює.
    }

    @Override
    public void stop(SystemMediaPlayerEngine engine) {
        engine.stopPlayback();
    }
}