package net.lop223;

import net.lop223.utils.prototype.Prototype;
import net.lop223.utils.flyweight.TrackMetadata;
import net.lop223.utils.flyweight.TrackMetadataFactory;

import java.time.Duration;
import java.util.Objects;

/**
 * [GRASP: Information Expert, High Cohesion]
 * Представляє аудіотрек у системі. Зберігає лише метадані та шлях до файлу.
 */
public class Track implements Prototype<Track> {
    private final String id;
    private final String title;
    private final Duration duration;
    private final String sourcePath;
    private final TrackMetadata metadata;
    private final int bitrateKbps;

    public Track(String id, String title, String artist, String album, Duration duration, String sourcePath, String genre, int bitrateKbps) {
        this.id = Objects.requireNonNull(id);
        this.title = Objects.requireNonNull(title);
        this.metadata = TrackMetadataFactory.getMetadata(artist, album, genre);
        this.duration = Objects.requireNonNull(duration);
        this.sourcePath = Objects.requireNonNull(sourcePath);
        this.bitrateKbps = bitrateKbps;
    }

    @Override
    public Track clone() {
        return new Track(id + "_copy", title, getArtist(), getAlbum(), duration, sourcePath, getGenre(), bitrateKbps);
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return metadata.artist(); }
    public String getAlbum() { return metadata.album(); }
    public Duration getDuration() { return duration; }
    public String getSourcePath() { return sourcePath; }
    public String getGenre() { return metadata.genre(); }
    public int getBitrateKbps() { return bitrateKbps; }
    public TrackMetadata getMetadata() { return metadata; }

    /**
     * [GRASP: Information Expert]
     * Трек сам знає, як форматувати свої метадані для відображення.
     */
    public String getFormattedDetails() {
        long minutes = duration.toMinutes();
        long seconds = duration.minusMinutes(minutes).getSeconds();
        return String.format("%s - %s [%02d:%02d] (Album: %s, Genre: %s, %d kbps)",
                getArtist(), title, minutes, seconds, getAlbum(), getGenre(), bitrateKbps);
    }

    @Override
    public String toString() {
        return getFormattedDetails();
    }
}