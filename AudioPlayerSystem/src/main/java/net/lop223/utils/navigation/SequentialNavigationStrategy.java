package net.lop223.utils.navigation;

import net.lop223.Track;

import java.util.List;

/**
 * [SOLID: OCP] Стандартне послідовне відтворення по колу.
 */
public class SequentialNavigationStrategy implements QueueNavigationStrategy {
    @Override
    public int getNextIndex(int currentIndex, List<Track> tracks) {
        if (tracks.isEmpty()) return -1;
        if (currentIndex < 0) return 0;
        return (currentIndex + 1) % tracks.size();
    }

    @Override
    public int getPreviousIndex(int currentIndex, List<Track> tracks) {
        if (tracks.isEmpty()) return -1;
        if (currentIndex <= 0) return tracks.size() - 1;
        return currentIndex - 1;
    }
}