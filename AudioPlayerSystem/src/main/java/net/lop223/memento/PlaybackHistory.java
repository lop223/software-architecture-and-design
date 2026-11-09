package net.lop223.memento;

import net.lop223.PlaybackQueueService;

import java.util.ArrayDeque;
import java.util.Deque;

public class PlaybackHistory {
    private final PlaybackQueueService queueService;
    private final Deque<PlaybackMemento> history = new ArrayDeque<>();

    public PlaybackHistory(PlaybackQueueService queueService) {
        this.queueService = queueService;
    }

    public void save() {
        history.push(queueService.createMemento());
    }

    public void restoreLast() {
        if (history.isEmpty()) {
            throw new IllegalStateException("Немає збереженого стану відтворення.");
        }
        queueService.restoreMemento(history.pop());
    }
}
