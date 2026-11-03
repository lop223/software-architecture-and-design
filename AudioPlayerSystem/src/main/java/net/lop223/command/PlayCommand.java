package net.lop223.command;

import net.lop223.utils.IPlaybackQueueService;

public class PlayCommand implements AudioCommand {
    private final IPlaybackQueueService queueService;

    public PlayCommand(IPlaybackQueueService queueService) {
        this.queueService = queueService;
    }

    @Override
    public void execute() {
        queueService.playCurrent();
    }
}