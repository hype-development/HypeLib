/*
 *
 *  *  MIT License
 *  *
 *  * Copyright (C) 2023 Negative Games
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in all
 *  * copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  * SOFTWARE.
 *  *
 *
 */

package games.negative.alumina.menu;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import games.negative.alumina.menu.base.AluminaMenu;
import games.negative.alumina.menu.base.MenuItem;
import games.negative.alumina.menu.holder.ChestMenuHolder;
import games.negative.alumina.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Represents a chest menu.
 */
public abstract class ChestMenu implements AluminaMenu {

    private final Inventory inventory;
    private final Map<Integer, MenuItem> items;
    private final Map<String, MenuItem> byKey;

    public ChestMenu(@NotNull String title, int rows) {
        Preconditions.checkArgument(rows > 0, "Rows must be greater than 0.");
        Preconditions.checkArgument(rows <= 6, "Rows must be less than or equal to 6.");

        int size = rows * 9;

        this.inventory = Bukkit.createInventory(new ChestMenuHolder(this), size, ColorUtil.translate(title));
        this.items = Maps.newHashMap();
        this.byKey = Maps.newHashMap();
    }

    @Override
    public void setItem(int slot, @NotNull ItemStack item, @Nullable String functionKey) {
        items.remove(slot);

        applyFunction(item, functionKey);

        MenuItem menuItem = new MenuItem(item, functionKey);
        items.put(slot, menuItem);

        if (functionKey == null) return;

        byKey.put(functionKey, menuItem);
    }

    /**
     * Applies the function key to the item's data.
     * @param item The item.
     * @param key The function key.
     */
    private void applyFunction(@NotNull ItemStack item, @Nullable String key) {
        if (key == null) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(AluminaMenu.FUNCTION_KEY, PersistentDataType.STRING, key);

        item.setItemMeta(meta);
    }

    @Override
    public void clearSlot(int slot) {
        inventory.clear(slot);
    }

    @Override
    public void open(@NotNull Player player) {
        items.forEach((index, item) -> inventory.setItem(index, item.item()));

        player.openInventory(inventory);
    }

    /**
     * Get all menu items.
     * @return The menu items.
     */
    public Map<Integer, MenuItem> getItems() {
        return items;
    }

    public MenuItem byKey(@NotNull String key) {
        return byKey.getOrDefault(key, null);
    }
}
