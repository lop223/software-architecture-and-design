package net.lop223;

import java.util.*;

/**
 * [GRASP: Creator, High Cohesion]
 * Профіль користувача. Створює та керує своїми списками відтворення.
 */
class User {
    private final String id;
    private final String username;
    private final List<Playlist> playlists = new ArrayList<>();

    public User(String id, String username) {
        this.id = Objects.requireNonNull(id);
        this.username = Objects.requireNonNull(username);
    }

    public String getId() { return id; }
    public String getUsername() { return username; }
    public List<Playlist> getPlaylists() { return Collections.unmodifiableList(playlists); }


    /**
     * [GRASP: Creator]
     * User містить плейлисти та відповідає за створення їхніх екземплярів.
     */
    public Playlist createPlaylist(String name) {
        System.out.println("[STUB] User.createPlaylist() -> User '" + username + "' create playlist '" + name + "'");
        Playlist playlist = new Playlist(UUID.randomUUID().toString(), name);
        playlists.add(playlist);
        return playlist;
    }
}
