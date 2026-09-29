package net.lop223;

import net.lop223.utils.AudioDevice;
import net.lop223.utils.IPlaybackQueueService;
import net.lop223.utils.player_engine.IAudioDeviceManager;
import net.lop223.utils.player_engine.IAudioPlaybackControl;
import net.lop223.utils.player_engine.IAudioVolumeControl;
import net.lop223.utils.player_engine.IMediaPlayerEngine;
import net.lop223.utils.IMetadataReader;
import net.lop223.utils.IPlaylistRepository;

/**
 * [GRASP: Controller, Low Coupling]
 * Приймає запити від UI або консольного інтерфейсу та координує роботу підсистем.
 */
public class PlayerController {
    // [DIP]: Усі залежності є виключно інтерфейсами
    private final IPlaybackQueueService queueService;
    private final IAudioPlaybackControl playbackControl;
    private final IAudioVolumeControl volumeControl;
    private final IAudioDeviceManager deviceManager;
    private final IMetadataReader metadataReader;
    private final IPlaylistRepository playlistRepository;

    public PlayerController(
            IPlaybackQueueService queueService,
            IMediaPlayerEngine audioEngine,
            IMetadataReader metadataReader,
            IPlaylistRepository playlistRepository) {
        this.queueService = queueService;
        this.playbackControl = audioEngine;
        this.volumeControl = audioEngine;
        this.deviceManager = audioEngine;
        this.metadataReader = metadataReader;
        this.playlistRepository = playlistRepository;
    }

    public void onSelectPlaylist(Playlist playlist) {
        System.out.println("\n[UI Event] Choice playlist: " + playlist.getName());
        queueService.setPlaylist(playlist, 0);
        playlistRepository.save(playlist);
    }

    public void onPlayClicked() {
        System.out.println("\n[UI Event] Tap play button [ Play ]");
        queueService.playCurrent();
    }

    public void onPauseClicked() {
        System.out.println("\n[UI Event] Tap button [ Pause ]");
        playbackControl.pause();
    }

    public void onNextClicked() {
        System.out.println("\n[UI Event] Tap button [ Next Track >> ]");
        queueService.next();
    }

    public void onVolumeChanged(float volume) {
        System.out.println("\n[UI Event] Change volume tracker: " + (int)(volume * 100) + "%");
        volumeControl.setVolume(volume);
    }

    public void onDeviceSelected(AudioDevice device) {
        System.out.println("\n[UI Event] Choice audio device: " + device.name());
        deviceManager.setOutputDevice(device);
    }

    public Track importFileToPlaylist(String filePath, Playlist playlist) {
        System.out.println("\n[UI Event] Importing an audio file from a disk: " + filePath);
        Track track = metadataReader.readTrackFromFile(filePath);
        playlist.addTrack(track);
        return track;
    }
}
