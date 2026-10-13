package net.lop223.bridge;

import net.lop223.utils.AudioDevice;

public class WindowsAudioBackend implements AudioPlaybackBackend {
    @Override
    public void loadSource(String sourcePath) {
        System.out.println("[Bridge: WindowsBackend] Loading source: " + sourcePath);
    }

    @Override
    public void play() {
        System.out.println("[Bridge: WindowsBackend] Play");
    }

    @Override
    public void pause() {
        System.out.println("[Bridge: WindowsBackend] Pause");
    }

    @Override
    public void stop() {
        System.out.println("[Bridge: WindowsBackend] Stop");
    }

    @Override
    public void setVolume(float volume) {
        System.out.println("[Bridge: WindowsBackend] Volume set to: " + volume);
    }

    @Override
    public void setDevice(AudioDevice device) {
        System.out.println("[Bridge: WindowsBackend] Output device switched to: " + device.name());
    }
}
