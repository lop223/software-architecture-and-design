package net.lop223.command;

import net.lop223.utils.IPlaybackQueueService;
import net.lop223.utils.player_engine.IAudioPlaybackControl;

public class PauseCommand implements AudioCommand {
    private final IAudioPlaybackControl  queueService;

    public PauseCommand(IAudioPlaybackControl queueService) {
        this.queueService = queueService;
    }

    @Override
    public void execute() {
        queueService.pause();
    }
}