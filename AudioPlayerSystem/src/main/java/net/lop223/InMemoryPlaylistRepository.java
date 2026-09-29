package net.lop223;

import net.lop223.utils.IPlaylistRepository;

import java.util.*;

public class InMemoryPlaylistRepository implements IPlaylistRepository {
    private final Map<String, Playlist> storage = new HashMap<>();

    @Override
    public void save(Playlist playlist) {
        System.out.println("[STUB] PlaylistRepository.save() -> Playlist save '" + playlist.getName() + "' у сховище");
        storage.put(playlist.getId(), playlist);
    }

    @Override
    public Optional<Playlist> findById(String id) {
        System.out.println("[STUB] PlaylistRepository.findById() -> Finding playlist ID: " + id);
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Playlist> findAll() {
        return new ArrayList<>(storage.values());
    }
}
