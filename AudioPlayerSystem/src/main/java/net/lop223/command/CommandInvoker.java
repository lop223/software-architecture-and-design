package net.lop223.command;

public class CommandInvoker {
    public void execute(AudioCommand command) {
        command.execute();
    }
}
