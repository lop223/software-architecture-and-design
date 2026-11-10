package net.lop223.state;

import net.lop223.SystemMediaPlayerEngine;
import net.lop223.utils.PlaybackState;

public final class PlayingState implements MediaPlayerState {
    public static final PlayingState INSTANCE = new PlayingState();

    private PlayingState() {}

    @Override
    public PlaybackState getStatus() {
        return PlaybackState.PLAYING;
    }

    @Override
    public void play(SystemMediaPlayerEngine engine) {
        // Плеєр уже відтворює трек.
    }

    @Override
    public void pause(SystemMediaPlayerEngine engine) {
        engine.pausePlayback();
    }

    @Override
    public void stop(SystemMediaPlayerEngine engine) {
        engine.stopPlayback();
    }
}