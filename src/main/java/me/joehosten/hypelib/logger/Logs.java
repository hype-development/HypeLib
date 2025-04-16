package me.joehosten.hypelib.logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public enum Logs {
    INFO(ChatColor.AQUA),
    WARNING(ChatColor.YELLOW),
    SEVERE(ChatColor.RED),
    SUCCESS(ChatColor.GREEN);

    private final ChatColor color;
    private static boolean disabled = false;

    Logs(ChatColor color) {
        this.color = color;
    }

    /**
     * Prints a log message with the given content and force flag.
     *
     * @param content The content of the log message.
     * @param force   true to force the log message to be printed, false to print the log message only if not disabled.
     */
    public void print(@NotNull String content, boolean force) {
        if (disabled && !force) return;

        String prefixedContent = "[HypeLib] " + color + content;
        Bukkit.getConsoleSender().sendMessage(prefixedContent);
    }

    /**
     * Prints a log message with the given content.
     *
     * @param content The content of the log message.
     */
    public void print(@NotNull String content) {
        print(content, false);
    }

    /**
     * Sets the disabled status for log messages.
     *
     * @param disabled true to disable log messages, false to enable them.
     */
    public static void setDisabled(boolean disabled) {
        Logs.disabled = disabled;
    }

    /**
     * Logs an info message.
     *
     * @param content The content of the log message.
     */
    public static void info(@NotNull String content) {
        INFO.print(content);
    }

    public static void info(@NotNull String content, boolean force) {
        INFO.print(content, force);
    }

    /**
     * Logs a warning message.
     *
     * @param content The content of the log message.
     */
    public static void warning(@NotNull String content) {
        WARNING.print(content);
    }

    public static void warning(@NotNull String content, boolean force) {
        WARNING.print(content, force);
    }

    /**
     * Logs a severe message.
     *
     * @param content The content of the log message.
     */
    public static void severe(@NotNull String content) {
        SEVERE.print(content);
    }

    public static void severe(@NotNull String content, boolean force) {
        SEVERE.print(content, force);
    }

    /**
     * Logs a success message.
     *
     * @param content The content of the log message.
     */
    public static void success(@NotNull String content) {
        SUCCESS.print(content);
    }

    public static void success(@NotNull String content, boolean force) {
        SUCCESS.print(content, force);
    }
}