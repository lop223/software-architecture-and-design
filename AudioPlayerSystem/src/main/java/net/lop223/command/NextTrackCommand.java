package net.lop223.command;

import net.lop223.utils.IPlaybackQueueService;

public class NextTrackCommand implements AudioCommand {
    private final IPlaybackQueueService queueService;

    public NextTrackCommand(IPlaybackQueueService queueService) {
        this.queueService = queueService;
    }

    @Override
    public void execute() {
        queueService.next();
    }
}