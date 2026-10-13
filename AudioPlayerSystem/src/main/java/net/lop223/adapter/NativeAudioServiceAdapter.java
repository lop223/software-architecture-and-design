package net.lop223.adapter;

import net.lop223.bridge.AudioPlaybackBackend;
import net.lop223.utils.AudioDevice;

public class NativeAudioServiceAdapter implements AudioPlaybackBackend {
    private final WindowsNativeAudioService nativeAudioService;

    public NativeAudioServiceAdapter(WindowsNativeAudioService nativeAudioService) {
        this.nativeAudioService = nativeAudioService;
    }

    @Override
    public void loadSource(String sourcePath) {
        nativeAudioService.openFile(sourcePath);
    }

    @Override
    public void play() {
        nativeAudioService.startPlayback();
    }

    @Override
    public void pause() {
        nativeAudioService.pausePlayback();
    }

    @Override
    public void stop() {
        nativeAudioService.stopPlayback();
    }

    @Override
    public void setVolume(float volume) {
        nativeAudioService.setNativeVolume(volume);
    }

    @Override
    public void setDevice(AudioDevice device) {
        nativeAudioService.switchDevice(device.deviceId());
    }
}
