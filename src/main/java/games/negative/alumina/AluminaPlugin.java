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

package games.negative.alumina;

import games.negative.alumina.command.builder.CommandBuilder;
import games.negative.alumina.command.structure.AluminaCommand;
import games.negative.alumina.listener.MenuListener;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * This is considered the main class of any project using alumina.
 */
public abstract class AluminaPlugin extends JavaPlugin {

    private static AluminaPlugin instance;

    /**
     * This method is called when the plugin is enabled.
     */
    public abstract void enable();

    /**
     * This method is called when the plugin is disabled.
     */
    public abstract void disable();

    /**
     * This method is used to register a command.
     * <p>
     * You do not need to register commands in the plugin.yml because this method will do it for you.
     * <p>
     * This method will also unregister any existing commands with the same name.
     * <p>
     *
     * @param builder The builder used to create the command.
     */
    public void registerCommand(@NotNull CommandBuilder builder) {
        Server server = Bukkit.getServer();
        Field field;
        try {
            field = server.getClass().getDeclaredField("commandMap");
        } catch (NoSuchFieldException e) {
            getLogger().severe("Could not retrieve the command map. (No Such Field)");
            return;
        }

        field.setAccessible(true);

        CommandMap commandMap;
        try {
            commandMap = (CommandMap) field.get(server);
        } catch (IllegalAccessException e) {
            getLogger().severe("Could not retrieve the command map. (Illegal Access)");
            return;
        }

        String name = builder.getName();

        Command existing = commandMap.getCommand(name);
        if (existing != null) {
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

        AluminaCommand command = builder.build();
        commandMap.register(name, command);
    }

    public void registerListeners(@NotNull Listener... listeners) {
        PluginManager manager = Bukkit.getPluginManager();

        for (Listener listener : listeners) {
            manager.registerEvents(listener, this);
        }
    }

    @Override
    public void onEnable() {
        instance = this;
        registerListeners(
                new MenuListener()
        );

        enable();
    }

    @Override
    public void onDisable() {
        disable();
    }

    public static AluminaPlugin getAluminaInstance() {
        return instance;
    }
}
