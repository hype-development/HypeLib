/*
 *  MIT License
 *
 * Copyright (C) 2025 Negative Games
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

package me.joehosten.hypelib.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * This class is used to represent the context of a command when executed.
 * @param args The arguments of the command
 * @param sender The sender of the command
 */
public record CommandContext(@NotNull String[] args, @NotNull CommandSender sender) {

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
     * Returns the length of the arguments.
     * @return the length of the arguments.
     */
    public int length() {
        return args.length;
    }

}
