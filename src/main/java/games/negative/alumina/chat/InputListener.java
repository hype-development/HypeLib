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

public class InputListener {

    private static final Map<UUID, InputListenerResponse> listeners = Maps.newHashMap();

    static {
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

        Events.listen(PlayerQuitEvent.class, event -> listeners.remove(event.getPlayer().getUniqueId()));
    }

    @FunctionalInterface
    private interface InputListenerResponse {

        void process(@NotNull Player player, @NotNull String message);

    }
}
