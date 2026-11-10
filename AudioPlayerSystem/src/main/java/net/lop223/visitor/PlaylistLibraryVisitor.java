package net.lop223.visitor;

import net.lop223.Playlist;
import net.lop223.PlaylistFolder;

public interface PlaylistLibraryVisitor<T> {
    T visit(Playlist playlist);
    T visit(PlaylistFolder folder);
}
