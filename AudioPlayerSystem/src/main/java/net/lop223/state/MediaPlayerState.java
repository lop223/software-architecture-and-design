package net.lop223.state;

import net.lop223.SystemMediaPlayerEngine;
import net.lop223.utils.PlaybackState;

public interface MediaPlayerState {
    PlaybackState getStatus();
    void play(SystemMediaPlayerEngine engine);
    void pause(SystemMediaPlayerEngine engine);
    void stop(SystemMediaPlayerEngine engine);
}
