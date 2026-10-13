package net.lop223;

import net.lop223.bridge.AudioPlaybackBackend;
import net.lop223.bridge.WindowsAudioBackend;
import net.lop223.stream_connection.AudioConnectionPool;
import net.lop223.stream_connection.AudioStreamConnection;
import net.lop223.utils.AudioDevice;
import net.lop223.utils.player_engine.IMediaPlayerEngine;
import net.lop223.utils.PlaybackState;

import java.time.Duration;
import java.util.List;

/**
 * [GRASP: Polymorphism]
 * Реалізація аудіорушія для операційної системи.
 *
 * [Bridge Pattern]
 * Абстракція (SystemMediaPlayerEngine) делегує роботу конкретному бекенду через AudioPlaybackBackend.
 * Це дозволяє змінювати реалізацію аудіо-API без зміни верхнього рівня плеєра.
 */
public class SystemMediaPlayerEngine implements IMediaPlayerEngine {
    private final AudioPlaybackBackend backend;
    private PlaybackState state = PlaybackState.STOPPED;
    private float volume = 0.8f;
    private AudioDevice currentDevice;
    private String loadedSourcePath;

    // Інтеграція з пулом об'єктів
    private final AudioConnectionPool connectionPool = new AudioConnectionPool(2);
    private AudioStreamConnection currentConnection;

    public SystemMediaPlayerEngine() {
        this(new WindowsAudioBackend());
    }

    public SystemMediaPlayerEngine(AudioPlaybackBackend backend) {
        this.backend = backend;
        this.currentDevice = new AudioDevice("dev_default", "Вбудовані динаміки", true);

        AudioPlayerConfig config = AudioPlayerConfig.getInstance();
        System.out.println("[SystemAudioEngine] Config applied: Buffer size = " + config.getBufferSize() + " bytes");
    }

    @Override
    public PlaybackState getState() { return state; }

    @Override
    public float getVolume() { return volume; }

    @Override
    public void setVolume(float volume) {
        this.volume = Math.clamp(volume, 0.0f, 1.0f);
        backend.setVolume(this.volume);
        System.out.println("[Bridge] AudioEngine.setVolume() -> Volume set to: " + (int)(this.volume * 100) + "%");
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
        backend.setDevice(device);
        System.out.println("[Bridge] AudioEngine.setOutputDevice() -> The stream has been redirected to: " + device.name());
    }

    @Override
    public void loadSource(String sourcePath) {
        this.loadedSourcePath = sourcePath;
        backend.loadSource(sourcePath);

        if (currentConnection != null) {
            connectionPool.release(currentConnection);
        }

        this.currentConnection = connectionPool.acquire();
        this.currentConnection.bindStream(sourcePath);
        System.out.println("[AudioEngine] File ready for playback via " + currentConnection.getConnectionId());
    }

    @Override
    public void play() {
        if (loadedSourcePath == null) {
            System.out.println("[AudioEngine WARN] No source loaded!");
            return;
        }
        this.state = PlaybackState.PLAYING;
        backend.play();
        System.out.println("[AudioEngine] PLAYING on '" + currentDevice.name() + "' [Stream: " + currentConnection.getConnectionId() + "]");
    }

    @Override
    public void pause() {
        this.state = PlaybackState.PAUSED;
        backend.pause();
        System.out.println("[STUB] AudioEngine.pause() -> PAUSE");
    }

    @Override
    public void stop() {
        this.state = PlaybackState.STOPPED;
        backend.stop();
        if (currentConnection != null) {
            connectionPool.release(currentConnection);
            currentConnection = null;
        }
        System.out.println("[AudioEngine] STOPPED (Connection released back to pool)");
    }

    @Override
    public void seek(Duration position) {
        System.out.println("[STUB] AudioEngine.seek() -> Seek to position: " + position.toSeconds() + " sec");
    }
}