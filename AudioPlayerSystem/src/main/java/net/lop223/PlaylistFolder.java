package net.lop223;

import net.lop223.visitor.PlaylistLibraryVisitor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Composite library node that groups playlists and nested folders.
 */
public class PlaylistFolder implements PlaylistLibraryItem {
    private final String name;
    private final List<PlaylistLibraryItem> items = new ArrayList<>();

    public PlaylistFolder(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public String getName() {
        return name;
    }

    public List<PlaylistLibraryItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(PlaylistLibraryItem item) {
        Objects.requireNonNull(item);
        if (item == this || containsFolder(item, this)) {
            throw new IllegalArgumentException("Adding this item would create a folder cycle.");
        }
        items.add(item);
    }

    public boolean remove(PlaylistLibraryItem item) {
        return items.remove(item);
    }

    @Override
    public List<Track> getTracks() {
        List<Track> tracks = new ArrayList<>();
        for (PlaylistLibraryItem item : items) {
            tracks.addAll(item.getTracks());
        }
        return List.copyOf(tracks);
    }

    @Override
    public Duration calculateTotalDuration() {
        Duration total = Duration.ZERO;
        for (PlaylistLibraryItem item : items) {
            total = total.plus(item.calculateTotalDuration());
        }
        return total;
    }

    private static boolean containsFolder(PlaylistLibraryItem item, PlaylistFolder target) {
        if (!(item instanceof PlaylistFolder folder)) {
            return false;
        }
        if (folder == target) {
            return true;
        }
        for (PlaylistLibraryItem child : folder.items) {
            if (containsFolder(child, target)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public <T> T accept(PlaylistLibraryVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
