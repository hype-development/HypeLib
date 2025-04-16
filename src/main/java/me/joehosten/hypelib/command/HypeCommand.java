package me.joehosten.hypelib.command;

import me.joehosten.hypelib.AluminaPlugin;
import me.joehosten.hypelib.logger.Logs;

public abstract class HypeCommand extends Command {

    protected final AluminaPlugin plugin;

    public HypeCommand(AluminaPlugin plugin) {
        Logs.info("Initializing command: " + this.getClass().getSimpleName());
        this.plugin = plugin;
    }

}