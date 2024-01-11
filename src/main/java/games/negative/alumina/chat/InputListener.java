package games.negative.alumina.chat;

import com.google.common.collect.Maps;
import games.negative.alumina.event.Events;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;

/**
 * The InputListener class is responsible for listening to player input events, such as chat messages and commands.
 * It provides a static method to register input listeners and a functional interface for handling input responses.
 * When a player sends a chat message or command,
 * the InputListener will check if there is a registered listener for that player
 * and execute the corresponding response.
 */
public class InputListener {

    private static final Map<UUID, InputListenerResponse> listeners = Maps.newHashMap();

    static {
        // Listen to chat messages
        Events.listen(AsyncPlayerChatEvent.class, event -> {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            if (!listeners.containsKey(uuid)) return;

            InputListenerResponse response = listeners.get(uuid);
            if (response == null) return;

            try {
                event.setCancelled(true);
                response.process(player, event.getMessage());
            } catch (Exception ignored) {} finally {
                listeners.remove(uuid);
            }
        });

        // Listen to command events
        Events.listen(PlayerCommandPreprocessEvent.class, event -> {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            if (!listeners.containsKey(uuid)) return;

            InputListenerResponse response = listeners.get(uuid);
            if (response == null) return;

            try {
                event.setCancelled(true);
                response.process(player, event.getMessage());
            } catch (Exception ignored) {} finally {
                listeners.remove(uuid);
            }
        });

        // Remove the listener when the player quits the server
        Events.listen(PlayerQuitEvent.class, event -> listeners.remove(event.getPlayer().getUniqueId()));
    }

    /**
     * Registers an input listener for a specific player.
     *
     * @param uuid The UUID of the player to register the listener for.
     * @param response The input listener response that will be executed when the player sends a chat message or command.
     */
    public static void listen(@NotNull UUID uuid, @NotNull InputListenerResponse response) {
        listeners.put(uuid, response);
    }

    /**
     * Functional interface for handling input responses from players.
     */
    @FunctionalInterface
    public interface InputListenerResponse {

        /**
         * Process the input message from a player.
         *
         * @param player The player object from whom the message is received.
         * @param message The input message received from the player.
         */
        void process(@NotNull Player player, @NotNull String message);

    }
}
