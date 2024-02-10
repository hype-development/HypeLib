/*
 *  MIT License
 *
 * Copyright (C) 2024 Negative Games
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package games.negative.alumina.command;

import com.google.common.base.Preconditions;
import games.negative.alumina.future.BukkitFuture;
import games.negative.alumina.util.ColorUtil;
import games.negative.alumina.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;


/**
 * This class is used to represent the context of a command when executed.
 * @param args The arguments of the command
 * @param sender The sender of the command
 */
public record Context(String[] args, CommandSender sender) {

    /**
     * Returns the player who executed the command.
     * @return the player who executed the command.
     */
    @NotNull
    public Optional<Player> player() {
        return sender() instanceof Player ? Optional.of((Player) sender()) : Optional.empty();
    }

    /**
     * Returns the argument at the specified index.
     * @param index The index of the argument.
     * @return the argument at the specified index.
     */
    @NotNull
    public Optional<String> argument(final int index) {
        return (index >= args.length ? Optional.empty() : Optional.of(args[index]));
    }

    /**
     * Send a message to the sender of the command.
     * @param message The message to send.
     */
    public void message(@NotNull final String message) {
        Preconditions.checkNotNull(message, "message cannot be null");

        sender().sendMessage(ColorUtil.translate(message));
    }

    /**
     * Retrieves an OfflinePlayer object for the given username.
     *
     * @param username The username of the player.
     * @return An Optional object that provides the OfflinePlayer.
     * @throws NullPointerException if 'username' is null.
     */
    public Optional<OfflinePlayer> getOfflinePlayer(@NotNull String username) {
        Preconditions.checkNotNull(username, "username cannot be null");

        return Arrays.stream(Bukkit.getOfflinePlayers()).filter(offlinePlayer -> {
            String name = offlinePlayer.getName();
            return name != null && name.equalsIgnoreCase(username);
        }).findFirst();
    }

    /**
     * Retrieves an OfflinePlayer object for the given username using the Mojang API
     *
     * @param username The username of the player.
     * @return A BukkitFuture object that completes with the OfflinePlayer.
     * @throws NullPointerException if 'username' is null.
     */
    public BukkitFuture<OfflinePlayer> searchOfflinePlayer(@NotNull String username) {
        Preconditions.checkNotNull(username, "username cannot be null");

        return PlayerUtil.getOfflinePlayer(username);
    }
}
