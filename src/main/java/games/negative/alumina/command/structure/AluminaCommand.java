/*
 *  MIT License
 *
 * Copyright (C) 2023 Negative Games
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

package games.negative.alumina.command.structure;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import games.negative.alumina.command.Command;
import games.negative.alumina.command.Context;
import games.negative.alumina.command.builder.CommandBuilder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents the implementation of any alumina command.
 */
public class AluminaCommand extends org.bukkit.command.Command {

    private final Command component;
    private final List<AluminaCommand> subCommands;
    private final String[] permissions;
    private final String[] params;
    private final boolean playerOnly;
    private final AluminaCommand parent;

    /**
     * Creates a new alumina command.
     * @param parent The parent command.
     * @param builder The command builder.
     */
    public AluminaCommand(@Nullable AluminaCommand parent, @NotNull CommandBuilder builder) {
        super(builder.getName(), builder.getDescription(), builder.getUsage(), builder.getAliases());

        this.parent = parent;

        Preconditions.checkNotNull(builder.getComponent(), "Command component cannot be null.");

        this.component = builder.getComponent();
        this.subCommands = Lists.newArrayList();
        this.playerOnly = builder.isPlayerOnly();
        this.permissions = builder.getPermissions();
        this.params = builder.getParams();

        if (builder.getUsage() != null)
            this.setUsage(builder.getUsage());

        for (CommandBuilder cmd : builder.getSubCommands()) {
            AluminaCommand subCommand = new AluminaCommand(this, cmd);
            this.subCommands.add(subCommand);
        }
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, String[] args) {
        if (playerOnly && !(sender instanceof Player)) {
//            FrameworkMessage.COMMAND_CANNOT_USE_THIS_AS_CONSOLE.send(sender);
            return true;
        }

        if (!checkPermissions(sender)) {
//            FrameworkMessage.COMMAND_NO_PERMISSION.send(sender);
            return true;
        }

        if (!checkParams(sender, args))
            return true;

        if (checkSubCommands(sender, args))
            return true;

        Context context = new Context(getName(), args, sender);
        component.execute(context);
        return true;
    }

    private boolean checkPermissions(@NotNull CommandSender sender) {
        if (this.permissions == null)
            return true;

        for (String permission : this.permissions) {
            if (sender.hasPermission(permission))
                return true;
        }
        return false;
    }

    private boolean checkSubCommands(@NotNull CommandSender sender, @NotNull String[] args) {
        if (args.length == 0 || subCommands.isEmpty())
            return false;

        String begin = args[0];
        String[] snippet = Arrays.copyOfRange(args, 1, args.length);

        AluminaCommand subCommand = getAvailableSubCommand(begin);
        return (subCommand != null && subCommand.execute(sender, begin, snippet));
    }

    @Nullable
    public AluminaCommand getAvailableSubCommand(@NotNull String argument) {
        for (AluminaCommand subCommand : subCommands) {
            if (subCommand.getName().equalsIgnoreCase(argument) || subCommand.getAliases().contains(argument.toLowerCase()))
                return subCommand;
        }
        return null;
    }

    public boolean checkParams(@NotNull CommandSender sender, @NotNull String[] args) {
        if (this.params == null)
            return true;

        if (args.length < params.length) {
            StringBuilder builder = new StringBuilder();
            for (String param : params) {
                builder.append("<").append(param).append(">").append(" ");
            }

            List<String> parentNames = Lists.newArrayList();
            parentNames.add(getName());

            AluminaCommand search = this;
            while (search.getParent() != null) {
                parentNames.add(search.getParent().getName());
                search = search.getParent();
            }

            Collections.reverse(parentNames);

            StringBuilder parentBuilder = new StringBuilder();

            int iteration = 0;
            for (String parentName : parentNames) {
                if (iteration != 0)
                    parentBuilder.append(" ");

                parentBuilder.append(parentName);

                iteration++;
            }

//            FrameworkMessage.COMMAND_USAGE.replace("%command%", parentBuilder.toString()).replace("%usage%", builder.toString()).send(sender);
            return false;
        }

        return true;
    }

    public Command getComponent() {
        return component;
    }

    public List<AluminaCommand> getSubCommands() {
        return subCommands;
    }

    public String[] getParams() {
        return params;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public boolean isPlayerOnly() {
        return playerOnly;
    }

    public void addSubCommand(@NotNull AluminaCommand command) {
        this.subCommands.add(command);
    }

    public AluminaCommand getParent() {
        return parent;
    }

}
