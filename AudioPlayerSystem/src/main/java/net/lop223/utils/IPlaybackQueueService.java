package net.lop223.utils;

import net.lop223.Playlist;
import net.lop223.Track;

import java.util.Optional;

public interface IPlaybackQueueService {
    void setPlaylist(Playlist playlist, int startIndex);
    Optional<Track> getCurrentTrack();
    void playCurrent();
    void next();
    void previous();
}
