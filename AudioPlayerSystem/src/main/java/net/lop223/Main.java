package net.lop223;

import net.lop223.utils.AudioDevice;
import net.lop223.utils.player_engine.IMediaPlayerEngine;
import net.lop223.utils.IMetadataReader;
import net.lop223.utils.IPlaylistRepository;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("===================================================================");

        IMediaPlayerEngine audioEngine = new SystemMediaPlayerEngine();
        IMetadataReader metadataReader = new LocalFileMetadataReader();
        IPlaylistRepository repository = new InMemoryPlaylistRepository();

        PlaybackQueueService queueService = new PlaybackQueueService(audioEngine);

        PlayerController controller = new PlayerController(queueService, audioEngine, metadataReader, repository);

        User user = new User("usr_101", "Maks");
        Playlist rockPlaylist = user.createPlaylist("Rock Classics");

        controller.importFileToPlaylist("C:/Music/ACDC_Highway_To_Hell.mp3", rockPlaylist);
        controller.importFileToPlaylist("C:/Music/Nirvana_Smells_Like_Teen_Spirit.flac", rockPlaylist);

        Duration totalDuration = rockPlaylist.calculateTotalDuration();
        long minutes = totalDuration.toMinutes();
        long seconds = totalDuration.minusMinutes(minutes).getSeconds();
        System.out.printf("Playlist: '%s' | Tracks count: %d | Total duration: %02d:%02d\n",
                rockPlaylist.getName(), rockPlaylist.getTracks().size(), minutes, seconds);

        controller.onSelectPlaylist(rockPlaylist);
        controller.onPlayClicked();
        controller.onNextClicked();

        List<AudioDevice> devices = audioEngine.getAvailableDevices();
        AudioDevice bluetoothHeadphones = devices.stream()
                .filter(d -> d.name().contains("Bluetooth"))
                .findFirst()
                .orElse(devices.get(0));

        controller.onDeviceSelected(bluetoothHeadphones);
        controller.onVolumeChanged(0.65f);
        controller.onPauseClicked();

        System.out.println("\n===================================================================");

    }
}