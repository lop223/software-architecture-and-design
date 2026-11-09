package net.lop223.memento;

import net.lop223.Playlist;

public record PlaybackMemento(Playlist playlist, int currentIndex) {
}
