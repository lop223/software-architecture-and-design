package net.lop223.utils;

import java.time.Duration;
import java.util.List;

/**
 * [GRASP: Protected Variations, Polymorphism]
 * Інтерфейс медіа-рушія. Захищає ядро програми від змін у конкретних API аудіо.
 */
public interface IMediaPlayerEngine {
    PlaybackState getState();
    float getVolume();
    void setVolume(float volume);
    AudioDevice getCurrentDevice();

    List<AudioDevice> getAvailableDevices();
    void setOutputDevice(AudioDevice device);
    void loadSource(String sourcePath);
    void play();
    void pause();
    void stop();
    void seek(Duration position);
}