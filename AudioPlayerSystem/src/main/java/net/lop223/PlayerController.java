package net.lop223;

import net.lop223.importer.AudioImportHandler;
import net.lop223.mediator.PlaybackMediator;
import net.lop223.utils.AudioDevice;

/**
 * [GRASP: Controller, Low Coupling]
 * Приймає запити від UI або консольного інтерфейсу та координує роботу підсистем.
 */
public class PlayerController {
    // [DIP]: Усі залежності є виключно інтерфейсами
    private final PlaybackMediator playbackMediator;
    private final AudioImportHandler importHandler;


    public PlayerController(
            PlaybackMediator playbackMediator,
            AudioImportHandler importHandler) {
        this.playbackMediator = playbackMediator;
        this.importHandler = importHandler;
    }

    public void onSelectPlaylist(Playlist playlist) {
        playbackMediator.selectPlaylist(playlist);
    }

    public void onPlayClicked() {
        playbackMediator.play();
    }

    public void onPauseClicked() {
        playbackMediator.pause();
    }

    public void onNextClicked() {
        playbackMediator.next();
    }

    public void onVolumeChanged(float volume) {
        playbackMediator.changeVolume(volume);
    }

    public void onDeviceSelected(AudioDevice device) {
        playbackMediator.selectDevice(device);
    }

    public Track importFileToPlaylist(String filePath, Playlist playlist) {
        System.out.println("\n[UI Event] Importing an audio file from a disk: " + filePath);
        Track track = importHandler.handle(filePath);
        playlist.addTrack(track);
        return track;
    }
}
