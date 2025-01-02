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

package me.joehosten.hypelib;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import me.joehosten.hypelib.dependency.DependencyLoader;
import me.joehosten.hypelib.dependency.MavenDependency;
import me.joehosten.hypelib.dependency.MavenRepository;
import me.joehosten.hypelib.event.Events;
import me.joehosten.hypelib.logger.Logs;
import me.joehosten.hypelib.menu.config.YamlItemStack;
import me.joehosten.hypelib.menu.listener.MenuListener;
import me.joehosten.hypelib.util.FileLoader;
import lombok.Getter;
import me.joehosten.Sentinel.Sentinel;
import me.joehosten.Sentinel.builder.SentinelBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * This is considered the main class of any project using alumina.
 */
public abstract class AluminaPlugin extends JavaPlugin {

    private static AluminaPlugin instance;

    /**
     * This method is called when the plugin is initially loaded.
     */
    public abstract void load();

    /**
     * This method is called when the plugin is enabled.
     */
    public abstract void enable();

    /**
     * This method is called when the plugin is disabled.
     */
    public abstract void disable();

    public void registerCommand(@NotNull me.joehosten.hypelib.command.Command command) {
        Preconditions.checkNotNull(command, "Command cannot be null!");

        CommandMap commandMap = initCommandMap();
        if (commandMap == null) return;

        String name = command.getName();

        Command existing = commandMap.getCommand(name);
        if (existing != null) {
            cleanse(name, existing, commandMap);
        }

        commandMap.register(getName(), command);

        List<me.joehosten.hypelib.command.Command> sub = getRecursiveSubCommand(command);
        if (sub.isEmpty()) return;

        for (me.joehosten.hypelib.command.Command cmd : sub) {
            List<String> shortcuts = cmd.getShortcuts();
            if (shortcuts == null || shortcuts.isEmpty()) continue;

            for (String shortcut : shortcuts) {
                Command existingShortcut = commandMap.getCommand(shortcut);
                if (existingShortcut != null) {
                    cleanse(shortcut, existingShortcut, commandMap);
                }

                commandMap.register(shortcut, getName(), cmd);
            }
        }
    }

    /**
     * This method is used to get all subcommands of a command.
     * @param parent The parent command.
     * @return A list of all subcommands.
     */
    private List<me.joehosten.hypelib.command.Command> getRecursiveSubCommand(@NotNull me.joehosten.hypelib.command.Command parent) {
        Preconditions.checkNotNull(parent, "Parent command cannot be null!");

        // Recursively get all subcommands of all subcommands.
        List<me.joehosten.hypelib.command.Command> list = Lists.newArrayList(parent.getSubCommands());

        for (me.joehosten.hypelib.command.Command subCommand : parent.getSubCommands()) {
            List<me.joehosten.hypelib.command.Command> recursiveSubCommands = getRecursiveSubCommand(subCommand);
            if (recursiveSubCommands.isEmpty())
                break;

            list.addAll(recursiveSubCommands);
        }

        return list;
    }

    /**
     * This method is used to remove a command from the command map.
     * @param name The name of the command.
     * @param existing The existing command.
     * @param commandMap The command map.
     */
    private void cleanse(@NotNull String name, @NotNull Command existing, @NotNull CommandMap commandMap) {
        Preconditions.checkNotNull(name, "Command name cannot be null!");
        Preconditions.checkNotNull(existing, "Existing command cannot be null!");
        Preconditions.checkNotNull(commandMap, "Command map cannot be null!");

        Map<String, Command> map;
        try {
            map = (Map<String, Command>) commandMap.getClass().getDeclaredMethod("getKnownCommands").invoke(commandMap);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            getLogger().severe("Could not retrieve the command map. (Illegal Access, Invocation Target, No Such Method)");
            return;
        }

        existing.unregister(commandMap);
        map.remove(name);
        existing.getAliases().forEach(map::remove);
    }

    /**
     * This method is used to initialize the command map.
     * @return The command map.
     */
    private CommandMap initCommandMap() {
        Server server = Bukkit.getServer();
        Field field;
        try {
            field = server.getClass().getDeclaredField("commandMap");
        } catch (NoSuchFieldException e) {
            getLogger().severe("Could not retrieve the command map. (No Such Field)");
            return null;
        }

        field.setAccessible(true);

        CommandMap commandMap;
        try {
            commandMap = (CommandMap) field.get(server);
        } catch (IllegalAccessException e) {
            getLogger().severe("Could not retrieve the command map. (Illegal Access)");
            return null;
        }

        return commandMap;
    }

    /**
     * This method is used to register multiple listeners.
     * @param listeners The listeners to register.
     */
    public void registerListeners(@NotNull Listener... listeners) {
        Preconditions.checkNotNull(listeners, "Listeners cannot be null!");
        Preconditions.checkArgument(listeners.length > 0, "Listeners cannot be empty!");

        Events.listen(listeners);
    }

    /**
     * This method is used to register a listener.
     * @param listener The listener to register.
     */
    public void registerListener(@NotNull Listener listener) {
        Preconditions.checkNotNull(listener, "Listener cannot be null!");

        Events.listen(listener);
    }

    /**
     * This method is used to load a file from the plugin's resources folder.
     * @param name The name of the file to load.
     */
    public void loadFile(@NotNull String name) {
        FileLoader.loadFile(this, name);
    }

    /**
     * Loads a dependency into a JavaPlugin using the provided group id, artifact id, version.
     *
     * @param groupId    the group id of the dependency
     * @param artifactId the artifact id of the dependency
     * @param version    the version of the dependency
     */
    public void loadDependency(@NotNull String groupId, @NotNull String artifactId, @NotNull String version) {
        loadDependency(groupId, artifactId, version, DependencyLoader.CENTRAL.url());
    }

    /**
     * Loads a dependency into a JavaPlugin using the provided group id, artifact id, version, and repository URL.
     *
     * @param groupId    The group id of the dependency.
     * @param artifactId The artifact id of the dependency.
     * @param version    The version of the dependency.
     * @param repoUrl    The repository URL where the dependency is located.
     * @throws NullPointerException if `plugin`, `groupId`, `artifactId`, `version`, or `repoUrl` is null.
     * @throws RuntimeException if unable to load the dependency.
     */
    public void loadDependency(@NotNull String groupId, @NotNull String artifactId, @NotNull String version, @NotNull String repoUrl) {
        Preconditions.checkNotNull(groupId, "'groupId' cannot be null!");
        Preconditions.checkNotNull(artifactId, "'artifactId' cannot be null!");
        Preconditions.checkNotNull(version, "'version' cannot be null!");
        Preconditions.checkNotNull(repoUrl, "'repoUrl' cannot be null!");

        DependencyLoader.loadDependency(this, new MavenDependency(groupId, artifactId, version, new MavenRepository(repoUrl)));
    }

    public boolean licence(String productId, LicencePlatform platform, String platformValue) {
        SentinelBuilder builder = new SentinelBuilder(this).url("https://licence.auth.joehosten.me/api/v1").auth("ojftuh3npbvc9cpj3q8hjgfu60").platform(platform.getValue()).platformValue(platformValue).product(productId);
        Sentinel sentinel = builder.build();

        return sentinel.authenticate();
    }


    @Override
    public void onLoad() {
        instance = this;

        try {
            YamlItemStack.setGlowingEnchantment(Enchantment.CHANNELING);
        } catch (Exception ignored) {
            Logs.severe("Could not initialize glowing enchantment. (Exception)");
        }

        load();
    }

    @Override
    public void onEnable() {
        new MenuListener();

        enable();
    }

    @Override
    public void onDisable() {
        disable();
    }

    public static AluminaPlugin getAluminaInstance() {
        return instance;
    }

    @Getter
    public enum LicencePlatform {
        DISCORD("Discord"),
        BUILT_BY_BIT("BuiltByBit"),
        EMAIL("Email"),
        SPIGOT("SpigotMC");

        private final String value;

        LicencePlatform(String value) {
            this.value = value;
        }

    }
}
