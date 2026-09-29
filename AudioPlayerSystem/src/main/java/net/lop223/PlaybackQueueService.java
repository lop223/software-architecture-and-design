package net.lop223;

import net.lop223.utils.player_engine.IMediaPlayerEngine;

import java.util.Optional;

/**
 * [GRASP: Indirection, High Cohesion]
 * Керує чергою треків (Next, Previous, Shuffle, Repeat).
 * Виступає посередником між командами контролера та рушієм відтворення.
 */
class PlaybackQueueService {
    private final IMediaPlayerEngine audioEngine;
    private Playlist activePlaylist;
    private int currentIndex = -1;

    public PlaybackQueueService(IMediaPlayerEngine audioEngine) {
        this.audioEngine = audioEngine;
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
            audioEngine.play();
        });
    }

    public void next() {
        if (activePlaylist == null || activePlaylist.getTracks().isEmpty()) return;
        currentIndex = (currentIndex + 1) % activePlaylist.getTracks().size();
        System.out.println("[STUB] QueueService.next() -> Jump to the index track: " + currentIndex);
        loadCurrentTrack();
        audioEngine.play();
    }

    public void previous() {
        if (activePlaylist == null || activePlaylist.getTracks().isEmpty()) return;
        currentIndex = (currentIndex - 1 + activePlaylist.getTracks().size()) % activePlaylist.getTracks().size();
        System.out.println("[STUB] QueueService.previous() -> Jump to the index track: " + currentIndex);
        loadCurrentTrack();
        audioEngine.play();
    }

    private void loadCurrentTrack() {
        getCurrentTrack().ifPresent(track -> audioEngine.loadSource(track.getSourcePath()));
    }
}
