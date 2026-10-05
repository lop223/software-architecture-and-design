package net.lop223.builder;

import net.lop223.Track;

import java.time.Duration;
import java.util.UUID;

public class TrackBuilder {
    private String id;
    private String title;
    private String artist = "Unknown Artist";
    private String album = "Unknown Album";
    private Duration duration = Duration.ZERO;
    private String sourcePath;
    private String genre = "General";
    private int bitrateKbps = 320;

    public TrackBuilder() {
        this.id = UUID.randomUUID().toString();
    }

    public Track build() {
        if (title == null || title.isBlank()) {
            throw new IllegalStateException("Track title cannot be null or empty!");
        }
        if (sourcePath == null || sourcePath.isBlank()) {
            throw new IllegalStateException("Audio source file path is required!");
        }
        return new Track(id, title, artist, album, duration, sourcePath, genre, bitrateKbps);
    }

    public TrackBuilder id(String id) {
        this.id = id;
        return this;
    }

    public TrackBuilder title(String title) {
        this.title = title;
        return this;
    }

    public TrackBuilder artist(String artist) {
        this.artist = artist;
        return this;
    }

    public TrackBuilder album(String album) {
        this.album = album;
        return this;
    }

    public TrackBuilder sourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
        return this;
    }

    public TrackBuilder genre(String genre) {
        this.genre = genre;
        return this;
    }

    public TrackBuilder durations(Duration duration) {
        this.duration = duration;
        return this;
    }

    public TrackBuilder durationsSeconds(long seconds) {
        this.duration = Duration.ofSeconds(seconds);
        return this;
    }

    public TrackBuilder bitrate(int bitrateKbps) {
        this.bitrateKbps = bitrateKbps;
        return this;
    }
}
