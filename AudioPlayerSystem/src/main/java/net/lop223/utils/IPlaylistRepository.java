package net.lop223.utils;

import net.lop223.Playlist;

import java.util.List;
import java.util.Optional;

/**
 * [GRASP: Pure Fabrication, Low Coupling]
 * Збереження та отримання плейлистів.
 */
public interface IPlaylistRepository {
    void save(Playlist playlist);
    Optional<Playlist> findById(String id);
    List<Playlist> findAll();
}
