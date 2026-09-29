package net.lop223.utils.navigation;

import net.lop223.Track;

import java.util.List;

/**
 * [SOLID: OCP] Стратегія переходу між треками в черзі.
 * Дозволяє додавати нові режими (Shuffle, Repeat тощо) без модифікації коду черги.
 */
public interface QueueNavigationStrategy {
    int getNextIndex(int currentIndex, List<Track> tracks);
    int getPreviousIndex(int currentIndex, List<Track> tracks);
}
