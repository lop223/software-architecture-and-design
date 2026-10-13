package net.lop223.proxy;

import net.lop223.utils.AudioDevice;
import net.lop223.utils.PlaybackState;
import net.lop223.utils.player_engine.IMediaPlayerEngine;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Creates the real audio engine only when the first engine operation is requested.
 */
public final class LazyMediaPlayerEngineProxy implements IMediaPlayerEngine {
    private final Supplier<IMediaPlayerEngine> engineFactory;
    private volatile IMediaPlayerEngine engine;

    public LazyMediaPlayerEngineProxy(Supplier<IMediaPlayerEngine> engineFactory) {
        this.engineFactory = Objects.requireNonNull(engineFactory);
    }

    private IMediaPlayerEngine engine() {
        IMediaPlayerEngine current = engine;
        if (current == null) {
            synchronized (this) {
                current = engine;
                if (current == null) {
                    current = Objects.requireNonNull(engineFactory.get(), "Engine factory returned null.");
                    engine = current;
                }
            }
        }
        return current;
    }

    @Override
    public PlaybackState getState() {
        return engine().getState();
    }

    @Override
    public void loadSource(String sourcePath) {
        engine().loadSource(sourcePath);
    }

    @Override
    public void play() {
        engine().play();
    }

    @Override
    public void pause() {
        engine().pause();
    }

    @Override
    public void stop() {
        engine().stop();
    }

    @Override
    public void seek(Duration position) {
        engine().seek(position);
    }

    @Override
    public float getVolume() {
        return engine().getVolume();
    }

    @Override
    public void setVolume(float volume) {
        engine().setVolume(volume);
    }

    @Override
    public AudioDevice getCurrentDevice() {
        return engine().getCurrentDevice();
    }

    @Override
    public List<AudioDevice> getAvailableDevices() {
        return engine().getAvailableDevices();
    }

    @Override
    public void setOutputDevice(AudioDevice device) {
        engine().setOutputDevice(device);
    }
}
