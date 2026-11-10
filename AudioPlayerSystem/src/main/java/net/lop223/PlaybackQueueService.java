package net.lop223;

import net.lop223.memento.PlaybackMemento;
import net.lop223.utils.IPlaybackQueueService;
import net.lop223.utils.PlaybackState;
import net.lop223.utils.navigation.QueueNavigationStrategy;
import net.lop223.utils.navigation.SequentialNavigationStrategy;
import net.lop223.utils.player_engine.IAudioPlaybackControl;
import net.lop223.utils.player_engine.IMediaPlayerEngine;

import java.util.Objects;
import java.util.Optional;

/**
 * [GRASP: Indirection, High Cohesion]
 * Керує чергою треків (Next, Previous, Shuffle, Repeat).
 * Виступає посередником між командами контролера та рушієм відтворення.
 * [SOLID: ISP/DIP]
 * Залежить лише від інтерфейсу контролю відтворення
 */
public class PlaybackQueueService implements IPlaybackQueueService {
    private final IAudioPlaybackControl audioControl;
    private QueueNavigationStrategy navigationStrategy;
    private Playlist activePlaylist;
    private int currentIndex = -1;

    public PlaybackQueueService(IAudioPlaybackControl audioControl) {
        this(audioControl, new SequentialNavigationStrategy());
    }

    public PlaybackQueueService(IAudioPlaybackControl audioControl, QueueNavigationStrategy strategy) {
        this.audioControl = audioControl;
        this.navigationStrategy = strategy;
    }

    public void setNavigationStrategy(QueueNavigationStrategy navigationStrategy) {
        this.navigationStrategy = navigationStrategy;
    }

    public void setPlaylist(Playlist playlist, int startIndex) {
        this.activePlaylist = playlist;
        this.currentIndex = startIndex;
        System.out.println("[STUB] QueueService.setPlaylist() -> Active playlist: '" + playlist.getName() +
                "', треків: " + playlist.getTracks().size());
        loadCurrentTrack();
    }

    public Optional<Track> getCurrentTrack() {
        if (activePlaylist != null && currentIndex >= 0 && currentIndex < activePlaylist.getTracks().size()) {
            return Optional.of(activePlaylist.getTracks().get(currentIndex));
        }
        return Optional.empty();
    }

    public void playCurrent() {
        getCurrentTrack().ifPresent(track -> {
            System.out.println("[STUB] QueueService.playCurrent() -> Start track: " + track.getTitle());
            audioControl.play();
        });
    }

    @Override
    public void next() {
        if (activePlaylist == null || activePlaylist.getTracks().isEmpty()) return;
        this.currentIndex = navigationStrategy.getNextIndex(currentIndex, activePlaylist.getTracks());
        System.out.println("[QueueService] Перехід до індексу: " + currentIndex);
        loadCurrentTrack();
        audioControl.play();
    }

    @Override
    public void previous() {
        if (activePlaylist == null || activePlaylist.getTracks().isEmpty()) return;
        this.currentIndex = navigationStrategy.getPreviousIndex(currentIndex, activePlaylist.getTracks());
        System.out.println("[QueueService] Перехід до індексу: " + currentIndex);
        loadCurrentTrack();
        audioControl.play();
    }

    private void loadCurrentTrack() {
        getCurrentTrack().ifPresent(track -> audioControl.loadSource(track.getSourcePath()));
    }

    public PlaybackMemento createMemento() {
        return new PlaybackMemento(activePlaylist, currentIndex);
    }

    public void restoreMemento(PlaybackMemento memento) {
        Objects.requireNonNull(memento);

        if (audioControl.getState() == PlaybackState.PLAYING) {
            audioControl.stop();
        }

        this.activePlaylist = memento.playlist();
        this.currentIndex = memento.currentIndex();
        loadCurrentTrack();
    }
}
