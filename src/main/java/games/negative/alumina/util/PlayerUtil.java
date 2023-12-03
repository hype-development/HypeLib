package games.negative.alumina.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
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
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * Player utility to handle some player-related tasks.
 */
public class PlayerUtil {

    /**
     * Reset the player to default properties.
     * @param player The player to reset.
     */
    public static void reset(final Player player) {
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
     * @param player The player to reset.
     */
    private static void resetHealth(final Player player) {
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
     * @param player The player
     * @param collection The collection of items
     * @param failure When the inventory is full of a given item, this consumer is called
     * @param <T> The type of collection
     */
    private <T extends Iterable<ItemStack>> void fillInventory(final Player player, final T collection, final Consumer<ItemStack> failure) {
        Preconditions.checkNotNull(player, "'player' cannot be null!");
        Preconditions.checkNotNull(collection, "'collection' cannot be null!");

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

            // When the inventory is full of the item, call the failure consumer
            if (failure == null) continue;

            failure.accept(item);
        }

        player.updateInventory();
    }
}
