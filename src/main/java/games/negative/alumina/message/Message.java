/*
 *
 *  *  MIT License
 *  *
 *  * Copyright (C) 2023 Negative Games
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in all
 *  * copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  * SOFTWARE.
 *  *
 *
 */

package games.negative.alumina.message;

import com.google.common.base.Preconditions;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a message that can be sent to a {@link CommandSender}.
 * <p>
 * This supports color codes, placeholders, and hex colors.
 * <p>
 */
public class Message {

    /*
     * This pattern is used to match hex colors.
     */
    private static final Pattern PATTERN = Pattern.compile("#[a-fA-F0-9]{6}");

    /*
     * This is the default, unmodified message.
     */
    private final String def;

    /*
     * This is the current message with all modifications.
     */
    private String current;


    /**
     * Creates a new message.
     *
     * @param text The text of the message.
     */
    protected Message(@NotNull String text) {
        this.def = text;
        this.current = text;
    }

    /**
     * Replaces a placeholder with a replacement.
     *
     * @param placeholder The placeholder to replace.
     * @param replacement The replacement.
     * @return The message.
     */
    public Message replace(@NotNull String placeholder, @NotNull String replacement) {
        this.current = this.current.replace(placeholder, replacement);
        return this;
    }

    /**
     * Allow the message to be parsed by PlaceholderAPI.
     * @param player The player to parse the message for.
     * @return The message.
     * @throws IllegalStateException If PlaceholderAPI is not installed.
     */
    public Message parsePlaceholderAPI(@NotNull OfflinePlayer player) {
        Preconditions.checkState(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null, "PlaceholderAPI is not installed.");

        this.current = PlaceholderAPI.setPlaceholders(player, this.current);

        return this;
    }

    /**
     * Allow the message to be parsed by PlaceholderAPI.
     * @param player The player to parse the message for.
     * @return The message.
     * @throws IllegalStateException If PlaceholderAPI is not installed.
     */
    public Message parsePlaceholderAPI(@NotNull Player player) {
        Preconditions.checkState(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null, "PlaceholderAPI is not installed.");

        this.current = PlaceholderAPI.setPlaceholders(player, this.current);

        return this;
    }

    /**
     * Send the final message to a {@link CommandSender}.
     *
     * @param sender The sender to send the message to.
     */
    public void send(@NotNull CommandSender sender) {
        String translate = translate();
        String[] message = translate.split("\n");

        for (String line : message) {
            sender.sendMessage(line);
        }

        this.current = def;
    }

    /**
     * Broadcast the final message to the server.
     */
    public void broadcast() {
        String translate = translate();
        String[] message = translate.split("\n");

        for (String line : message) {
            Bukkit.broadcastMessage(line);
        }

        this.current = def;
    }

    /**
     * Statically create a new message instance using {@link Message#of(String...)}
     *
     * @param text The text of the message.
     * @return The message.
     */
    public static Message of(@NotNull String... text) {
        return new Message(String.join("\n", text));
    }

    /**
     * Statically create a new message instance using {@link Message#of(String)}
     *
     * @param text The text of the message.
     * @return The message.
     */
    public static Message of(@NotNull String text) {
        return new Message(text);
    }

    /**
     * Statically create a new message instance using {@link Message#of(List)}
     *
     * @param text The text of the message.
     * @return The message.
     */
    public static Message of(@NotNull List<String> text) {
        return new Message(String.join("\n", text));
    }

    /**
     * Translates the message to support color codes and hex colors.
     *
     * @return The translated message.
     */
    private String translate() {
        Matcher matcher = PATTERN.matcher(current);
        while (matcher.find()) {
            String color = current.substring(matcher.start(), matcher.end());
            current = current.replace(color, String.valueOf(ChatColor.of(color)));
            matcher = PATTERN.matcher(current);
        }
        return ChatColor.translateAlternateColorCodes('&', current);
    }
}
