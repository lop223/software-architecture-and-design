package net.lop223.decorator;

import net.lop223.utils.AudioDevice;
import net.lop223.utils.PlaybackState;
import net.lop223.utils.player_engine.IMediaPlayerEngine;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

/**
 * Adds a maximum-volume policy while preserving the media-engine interface.
 */
public final class VolumeLimitMediaPlayerDecorator implements IMediaPlayerEngine {
    private final IMediaPlayerEngine delegate;
    private final float maxVolume;

    public VolumeLimitMediaPlayerDecorator(IMediaPlayerEngine delegate, float maxVolume) {
        this.delegate = Objects.requireNonNull(delegate);
        if (!Float.isFinite(maxVolume) || maxVolume < 0.0f || maxVolume > 1.0f) {
            throw new IllegalArgumentException("Maximum volume must be between 0.0 and 1.0.");
        }
        this.maxVolume = maxVolume;
    }

    @Override
    public PlaybackState getState() {
        return delegate.getState();
    }

    @Override
    public void loadSource(String sourcePath) {
        delegate.loadSource(sourcePath);
    }

    @Override
    public void play() {
        delegate.play();
    }

    @Override
    public void pause() {
        delegate.pause();
    }

    @Override
    public void stop() {
        delegate.stop();
    }

    @Override
    public void seek(Duration position) {
        delegate.seek(position);
    }

    @Override
    public float getVolume() {
        return delegate.getVolume();
    }

    @Override
    public void setVolume(float volume) {
        if (!Float.isFinite(volume)) {
            throw new IllegalArgumentException("Volume must be a finite number.");
        }
        float limitedVolume = Math.clamp(volume, 0.0f, maxVolume);
        if (limitedVolume != volume) {
            System.out.println("[Decorator] Volume limited to " + (int) (limitedVolume * 100) + "%");
        }
        delegate.setVolume(limitedVolume);
    }

    @Override
    public AudioDevice getCurrentDevice() {
        return delegate.getCurrentDevice();
    }

    @Override
    public List<AudioDevice> getAvailableDevices() {
        return delegate.getAvailableDevices();
    }

    @Override
    public void setOutputDevice(AudioDevice device) {
        delegate.setOutputDevice(device);
    }
}
