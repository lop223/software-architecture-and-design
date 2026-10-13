package net.lop223.bridge;

import net.lop223.utils.AudioDevice;

public interface AudioPlaybackBackend {
    void loadSource(String sourcePath);
    void play();
    void pause();
    void stop();
    void setVolume(float volume);
    void setDevice(AudioDevice device);
}
