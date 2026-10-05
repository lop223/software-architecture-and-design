package net.lop223;

import net.lop223.utils.prototype.Prototype;

import java.time.Duration;
import java.util.Objects;

/**
 * [GRASP: Information Expert, High Cohesion]
 * Представляє аудіотрек у системі. Зберігає лише метадані та шлях до файлу.
 */
public class Track implements Prototype<Track> {
    private final String id;
    private final String title;
    private final String artist;
    private final String album;
    private final Duration duration;
    private final String sourcePath;
    private final String genre;
    private final int bitrateKbps;

    public Track(String id, String title, String artist, String album, Duration duration, String sourcePath, String genre, int bitrateKbps) {
        this.id = Objects.requireNonNull(id);
        this.title = Objects.requireNonNull(title);
        this.artist = Objects.requireNonNull(artist);
        this.album = Objects.requireNonNull(album);
        this.duration = Objects.requireNonNull(duration);
        this.sourcePath = Objects.requireNonNull(sourcePath);
        this.genre = Objects.requireNonNull(genre);
        this.bitrateKbps = bitrateKbps;
    }

    @Override
    public Track clone() {
        return new Track(id + "_copy", title, artist, album, duration, sourcePath, genre, bitrateKbps);
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getAlbum() { return album; }
    public Duration getDuration() { return duration; }
    public String getSourcePath() { return sourcePath; }
    public String getGenre() { return genre; }
    public int getBitrateKbps() { return bitrateKbps; }

    /**
     * [GRASP: Information Expert]
     * Трек сам знає, як форматувати свої метадані для відображення.
     */
    public String getFormattedDetails() {
        long minutes = duration.toMinutes();
        long seconds = duration.minusMinutes(minutes).getSeconds();
        return String.format("%s - %s [%02d:%02d] (Album: %s)", artist, title, minutes, seconds, album, genre, bitrateKbps);
    }

    @Override
    public String toString() {
        return getFormattedDetails();
    }
}