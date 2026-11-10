package net.lop223;

import net.lop223.visitor.PlaylistLibraryVisitor;

import java.time.Duration;
import java.util.List;

public interface PlaylistLibraryItem {
    String getName();
    List<Track> getTracks();
    Duration calculateTotalDuration();
    <T> T accept(PlaylistLibraryVisitor<T> visitor);
}
