package me.joehosten.hypelib.event;

import me.joehosten.hypelib.AluminaPlugin;
import me.joehosten.hypelib.logger.Logs;
import org.bukkit.event.Listener;

public abstract class HypeEvent implements Listener {

    protected final AluminaPlugin plugin;

    public HypeEvent(AluminaPlugin plugin) {
        Logs.info("Initializing event: " + this.getClass().getSimpleName());
        this.plugin = plugin;
    }
}