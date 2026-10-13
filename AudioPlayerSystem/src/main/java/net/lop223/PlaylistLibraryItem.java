package net.lop223;

import java.time.Duration;
import java.util.List;

public interface PlaylistLibraryItem {
    String getName();
    List<Track> getTracks();
    Duration calculateTotalDuration();
}
