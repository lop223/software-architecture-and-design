package net.lop223.observer;

@FunctionalInterface
public interface PlaybackObserver {
    void onPlaybackChanged(PlaybackEvent event);
}
