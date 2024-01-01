package games.negative.alumina.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Player utility to handle some player-related tasks.
 */
public class PlayerUtil {

    /**
     * Reset the player to default properties.
     *
     * @param player The player to reset.
     */
    public static void reset(@NotNull Player player) {
        Preconditions.checkNotNull(player, "'player' cannot be null!");

        for (PotionEffect effect : player.getActivePotionEffects())
            player.removePotionEffect(effect.getType());

        player.setWalkSpeed(0.2F);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.getOpenInventory().close();
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setHealth(20);
        resetHealth(player);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setExp(0);
        player.setLevel(0);
        player.setFireTicks(0);
        player.resetPlayerTime();
        player.resetPlayerWeather();
        player.setGameMode(GameMode.SURVIVAL);
        if (player.getVehicle() != null)
            player.leaveVehicle();

        if (!player.getPassengers().isEmpty())
            player.getPassengers().forEach(Entity::leaveVehicle);
    }

    /**
     * Reset the player's health.
     *
     * @param player The player to reset.
     */
    private static void resetHealth(@NotNull Player player) {
        Preconditions.checkNotNull(player, "'player' cannot be null!");

        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (attribute == null) return;

        Collection<AttributeModifier> modifiers = attribute.getModifiers();
        if (modifiers.isEmpty()) return;

        List<AttributeModifier> toRemove = Lists.newArrayList(modifiers);
        toRemove.forEach(attribute::removeModifier);
    }

    /**
     * Fills the player's inventory with the given collection of items
     *
     * @param player     The player
     * @param collection The collection of items
     * @param <T>        The type of collection
     * @return The collection of items that could not be added to the player's inventory
     */
    private <T extends Iterable<ItemStack>> Collection<ItemStack> fillInventory(@NotNull Player player, @NotNull T collection) {
        Preconditions.checkNotNull(player, "'player' cannot be null!");
        Preconditions.checkNotNull(collection, "'collection' cannot be null!");

        List<ItemStack> failed = Lists.newArrayList();

        PlayerInventory inv = player.getInventory();
        for (ItemStack item : collection) {
            // Make sure player's inventory isn't full
            if (inv.firstEmpty() != -1) {
                inv.addItem(item);
                continue;
            }

            int amount = item.getAmount();

            for (ItemStack content : inv.getContents()) {
                if (content == null || !content.isSimilar(item)) continue;

                int max = content.getMaxStackSize();
                int current = content.getAmount();

                int difference = Math.abs(max - current);
                if (amount > difference) {
                    content.setAmount(max);
                    amount -= difference;
                    continue;
                }

                content.setAmount(current + amount);
                amount = 0;
            }

            if (amount <= 0) continue;

            item.setAmount(amount);
            failed.add(item);
        }

        player.updateInventory();
        return failed;
    }

    /**
     * Get the player's UUID by their username.
     * @param username The username of the player.
     * @return The UUID of the player.
     * @throws IOException If an error occurs while getting the UUID.
     */
    public static UUID getByName(@NotNull String username) throws IOException {
        Preconditions.checkNotNull(username, "'username' cannot be null!");

        JsonObject response = HTTPUtil.get("https://api.mojang.com/users/profiles/minecraft/" + username);
        if (response == null) return null;

        String uuidStr = response.get("id").getAsString();
        return UUID.fromString(uuidStr);
    }

    /**
     * Checks if a player with the given UUID is online.
     *
     * @param uuid The UUID of the player.
     * @return true if the player is online, false otherwise.
     * @throws NullPointerException if 'uuid' is null.
     */
    public static boolean isOnline(@NotNull UUID uuid) {
        Preconditions.checkNotNull(uuid, "'uuid' cannot be null!");

        return Bukkit.getPlayer(uuid) != null;
    }

    /**
     * Checks if the given username is online.
     *
     * @param username The username to check.
     * @return {@code true} if the username is online, {@code false} otherwise.
     * @throws IOException If an error occurs while checking the status.
     */
    public static boolean isOnline(@NotNull String username) throws IOException {
        Preconditions.checkNotNull(username, "'username' cannot be null!");

        return Bukkit.getPlayer(username) != null;
    }

    /**
     * Retrieves a player by their UUID.
     *
     * @param uuid The UUID of the player.
     * @return Optional<Player> The player, or an empty Optional if the player is not found.
     * @throws NullPointerException if 'uuid' is null.
     */
    public static Optional<Player> getPlayer(@NotNull UUID uuid) {
        Preconditions.checkNotNull(uuid, "'uuid' cannot be null!");

        return Optional.ofNullable(Bukkit.getPlayer(uuid));
    }
}
