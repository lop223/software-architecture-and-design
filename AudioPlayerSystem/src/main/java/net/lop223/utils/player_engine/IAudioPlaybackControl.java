package net.lop223.utils.player_engine;

import net.lop223.utils.PlaybackState;

import java.time.Duration;

public interface IAudioPlaybackControl {
    PlaybackState getState();
    void loadSource(String sourcePath);
    void play();
    void pause();
    void stop();
    void seek(Duration position);
}
