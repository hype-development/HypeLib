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

package games.negative.alumina.event;

import com.google.common.base.Preconditions;
import games.negative.alumina.AluminaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;

import java.util.function.Consumer;

/**
 * Interface for more simply working with events and calling events.
 */
public interface Events extends Listener, EventExecutor {

    /**
     * Invoke an event.
     * @param event Event to invoke
     * @param <T> Event type
     */
    static <T extends Event> void call(final T event) {
        Preconditions.checkNotNull(event, "Event cannot be null");

        Bukkit.getPluginManager().callEvent(event);
    }

    /**
     * Listen to an event.
     * @param type Event type
     * @param listener Event listener
     * @return Events
     * @param <T> Event type
     */
    static <T extends Event> Events listen(final Class<T> type, final Consumer<T> listener) {
        Preconditions.checkNotNull(type, "Event type cannot be null");
        Preconditions.checkNotNull(listener, "Event listener cannot be null");

        return listen(type, EventPriority.NORMAL, listener);
    }

    /**
     * Listen to an event.
     * @param type Event type
     * @param priority Event priority
     * @param listener Event listener
     * @return Events
     * @param <T> Event type
     */
    static <T extends Event> Events listen(final Class<T> type, final EventPriority priority, final Consumer<T> listener) {
        Preconditions.checkNotNull(type, "Event type cannot be null");
        Preconditions.checkNotNull(priority, "Event priority cannot be null");
        Preconditions.checkNotNull(listener, "Event listener cannot be null");

        final Events events = ($, event) -> listener.accept(type.cast(event));

        Bukkit.getPluginManager().registerEvent(type, events, priority, events, AluminaPlugin.getAluminaInstance());

        return events;
    }

    /**
     * Listen to an event.
     * @param type Event type
     * @param priority Event priority
     * @param ignoreCancelled Ignore cancelled events
     * @param listener Event listener
     * @return Events
     * @param <T> Event type
     */
    static <T extends Event> Events listen(final Class<T> type, final EventPriority priority, final boolean ignoreCancelled, final Consumer<T> listener) {
        Preconditions.checkNotNull(type, "Event type cannot be null");
        Preconditions.checkNotNull(priority, "Event priority cannot be null");
        Preconditions.checkNotNull(listener, "Event listener cannot be null");

        final Events events = ($, event) -> listener.accept(type.cast(event));

        Bukkit.getPluginManager().registerEvent(type, events, priority, events, AluminaPlugin.getAluminaInstance(), ignoreCancelled);

        return events;
    }
}
