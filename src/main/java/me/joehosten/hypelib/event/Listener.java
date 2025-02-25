package me.joehosten.hypelib.event;

import me.joehosten.hypelib.logger.Logs;

public abstract class Listener implements org.bukkit.event.Listener {

    public Listener() {
        Logs.info("Initializing event: " + this.getClass().getSimpleName());
    }
}
