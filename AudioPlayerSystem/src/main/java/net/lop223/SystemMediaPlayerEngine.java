package net.lop223;

import net.lop223.utils.AudioDevice;
import net.lop223.utils.IMediaPlayerEngine;
import net.lop223.utils.PlaybackState;

import java.time.Duration;
import java.util.List;

/**
 * [GRASP: Polymorphism]
 * Реалізація аудіорушія для операційної системи.
 */
public class SystemMediaPlayerEngine implements IMediaPlayerEngine {
    private PlaybackState state = PlaybackState.STOPPED;
    private float volume = 0.8f;
    private AudioDevice currentDevice;
    private String loadedSourcePath;

    public SystemMediaPlayerEngine() {
        this.currentDevice = new AudioDevice("dev_default", "Вбудовані динаміки", true);
    }

    @Override
    public PlaybackState getState() { return state; }

    @Override
    public float getVolume() { return volume; }

    @Override
    public void setVolume(float volume) {
        this.volume = Math.clamp(volume, 0.0f, 1.0f);
        System.out.println("[STUB] AudioEngine.setVolume() -> Volume set to: " + (int)(this.volume * 100) + "%");
    }

    @Override
    public AudioDevice getCurrentDevice() { return currentDevice; }

    @Override
    public List<AudioDevice> getAvailableDevices() {
        System.out.println("[STUB] AudioEngine.getAvailableDevices() -> Scaning audio devices ОС...");
        return List.of(
                new AudioDevice("dev_01", "Вбудовані динаміки ", true),
                new AudioDevice("dev_02", "Bluetooth-навушники", false)
        );
    }

    @Override
    public void setOutputDevice(AudioDevice device) {
        this.currentDevice = device;
        System.out.println("[STUB] AudioEngine.setOutputDevice() -> The stream has been redirected to: " + device.name());
    }

    @Override
    public void loadSource(String sourcePath) {
        this.loadedSourcePath = sourcePath;
        System.out.println("[STUB] AudioEngine.loadSource() -> File loaded into the buffer: " + sourcePath);
    }

    @Override
    public void play() {
        if (loadedSourcePath == null) {
            System.out.println("[AudioEngine WARN] No audio file selected for startup.!");
            return;
        }
        this.state = PlaybackState.PLAYING;
        System.out.println("[STUB] AudioEngine.play() -> PLAYBACK via '" + currentDevice.name() + "'");
    }

    @Override
    public void pause() {
        this.state = PlaybackState.PAUSED;
        System.out.println("[STUB] AudioEngine.pause() -> PAUSE");
    }

    @Override
    public void stop() {
        this.state = PlaybackState.STOPPED;
        System.out.println("[STUB] AudioEngine.stop() -> STOPPED");
    }

    @Override
    public void seek(Duration position) {
        System.out.println("[STUB] AudioEngine.seek() -> Seek to position: " + position.toSeconds() + " sec");
    }
}