package net.lop223.bridge;

import net.lop223.utils.AudioDevice;

public class LinuxAudioBackend implements AudioPlaybackBackend {
    @Override
    public void loadSource(String sourcePath) {
        System.out.println("[Bridge: LinuxBackend] Loading source: " + sourcePath);
    }

    @Override
    public void play() {
        System.out.println("[Bridge: LinuxBackend] Play");
    }

    @Override
    public void pause() {
        System.out.println("[Bridge: LinuxBackend] Pause");
    }

    @Override
    public void stop() {
        System.out.println("[Bridge: LinuxBackend] Stop");
    }

    @Override
    public void setVolume(float volume) {
        System.out.println("[Bridge: LinuxBackend] Volume set to: " + volume);
    }

    @Override
    public void setDevice(AudioDevice device) {
        System.out.println("[Bridge: LinuxBackend] Output device switched to: " + device.name());
    }
}
