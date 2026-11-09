package net.lop223.mediator;

import net.lop223.Playlist;
import net.lop223.command.CommandInvoker;
import net.lop223.command.NextTrackCommand;
import net.lop223.command.PauseCommand;
import net.lop223.command.PlayCommand;
import net.lop223.observer.PlaybackEvent;
import net.lop223.observer.PlaybackObserver;
import net.lop223.utils.AudioDevice;
import net.lop223.utils.IPlaybackQueueService;
import net.lop223.utils.IPlaylistRepository;
import net.lop223.utils.player_engine.IAudioDeviceManager;
import net.lop223.utils.player_engine.IAudioPlaybackControl;
import net.lop223.utils.player_engine.IAudioVolumeControl;

import java.util.ArrayList;
import java.util.List;

public final class PlaybackMediator {
    private final IPlaybackQueueService queueService;
    private final IAudioPlaybackControl playbackControl;
    private final IAudioVolumeControl volumeControl;
    private final IAudioDeviceManager deviceManager;
    private final IPlaylistRepository playlistRepository;
    private final CommandInvoker commandInvoker;

    private final List<PlaybackObserver> observers = new ArrayList<>();

    public PlaybackMediator(
            IPlaybackQueueService queueService,
            IAudioPlaybackControl playbackControl,
            IAudioVolumeControl volumeControl,
            IAudioDeviceManager deviceManager,
            IPlaylistRepository playlistRepository,
            CommandInvoker commandInvoker) {
        this.queueService = queueService;
        this.playbackControl = playbackControl;
        this.volumeControl = volumeControl;
        this.deviceManager = deviceManager;
        this.playlistRepository = playlistRepository;
        this.commandInvoker = commandInvoker;
    }

    public void selectPlaylist(Playlist playlist) {
        queueService.setPlaylist(playlist, 0);
        playlistRepository.save(playlist);
        notifyObservers();
    }

    public void play() {
        commandInvoker.execute(new PlayCommand(queueService));
        notifyObservers();
    }

    public void pause() {
        commandInvoker.execute(new PauseCommand(playbackControl));
        notifyObservers();
    }

    public void next() {
        commandInvoker.execute(new NextTrackCommand(queueService));
        notifyObservers();
    }

    public void changeVolume(float volume) {
        volumeControl.setVolume(volume);
    }

    public void selectDevice(AudioDevice device) {
        deviceManager.setOutputDevice(device);
    }

    public void addObserver(PlaybackObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PlaybackObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        PlaybackEvent event = new PlaybackEvent(
                queueService.getCurrentTrack(),
                playbackControl.getState());

        observers.forEach(observer -> observer.onPlaybackChanged(event));
    }
}

