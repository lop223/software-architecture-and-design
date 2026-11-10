package net.lop223;

import net.lop223.utils.prototype.Prototype;
import net.lop223.visitor.PlaylistLibraryVisitor;

import java.time.Duration;
import java.util.*;

/**
 * [GRASP: Information Expert, Creator]
 * Список відтворення. Керує треками та обчислює сукупні властивості.
 */
public class Playlist implements Prototype<Playlist>, PlaylistLibraryItem, Iterable<Track> {
    private final String id;
    private String name;
    private final List<Track> tracks = new ArrayList<>();

    public Playlist(String id, String name) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public Playlist clone() {
        Playlist clone = new Playlist(UUID.randomUUID().toString(), name + " (Copy)");
        clone.tracks.addAll(tracks);
        return clone;
    }

    @Override
    public Iterator<Track> iterator() {
        return Collections.unmodifiableList(tracks).iterator();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Track> getTracks() { return Collections.unmodifiableList(tracks); }

    public void addTrack(Track track) {
        System.out.println("[STUB] Playlist.addTrack() -> Added track: '" + track.getTitle() + "' to playlist '" + name + "'");
        tracks.add(track);
    }

    public void removeTrack(String trackId) {
        System.out.println("[STUB] Playlist.removeTrack() -> removed track with ID: " + trackId);
        tracks.removeIf(t -> t.getId().equals(trackId));
    }

    /**
     * [GRASP: Information Expert]
     * Тільки Playlist володіє списком своїх треків, тому він обчислює загальний час звучання.
     */
    @Override
    public Duration calculateTotalDuration() {
        System.out.println("[STUB] Playlist.calculateTotalDuration() -> Підрахунок тривалості плейлиста '" + name + "'");
        Duration total = Duration.ZERO;
        for (Track track : tracks) {
            total = total.plus(track.getDuration());
        }
        return total;
    }

    @Override
    public <T> T accept(PlaylistLibraryVisitor<T> visitor) {
        return visitor.visit(this);
    }
}