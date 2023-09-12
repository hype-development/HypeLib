package games.negative.alumina.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * Represents a utility class used to update {@link ItemStack}s
 * without needing to write boilerplate code.
 */
public class ItemUpdater {

    /**
     * Update an item using a processor.
     * @param item Item to update
     * @param processor Processor to use
     */
    public static void of(@NotNull ItemStack item, @NotNull Consumer<ItemMeta> processor) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        processor.accept(meta);
        item.setItemMeta(meta);
    }

    /**
     * Update an item using a processor with a specified ItemMeta type.
     * @param item Item to update
     * @param type Type of the ItemMeta
     * @param processor Processor to use
     * @param <T> Type of the ItemMeta
     */
    public static <T extends ItemMeta> void of(@NotNull ItemStack item, @NotNull Class<T> type, @NotNull Consumer<T> processor) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        if (!type.isInstance(meta)) return;

        processor.accept(type.cast(meta));
        item.setItemMeta(meta);
    }

}
