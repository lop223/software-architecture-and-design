package net.lop223.utils.flyweight;

import java.util.Objects;

public record TrackMetadata(String artist, String album, String genre) {
    public TrackMetadata {
        Objects.requireNonNull(artist);
        Objects.requireNonNull(album);
        Objects.requireNonNull(genre);
    }
}
