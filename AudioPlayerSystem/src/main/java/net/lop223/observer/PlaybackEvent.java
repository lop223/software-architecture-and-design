package net.lop223.observer;

import net.lop223.Track;
import net.lop223.utils.PlaybackState;

import java.util.Optional;

public record PlaybackEvent(
        Optional<Track> track,
        PlaybackState state
) {
}
