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
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Represents a intractable chest menu.
 */
public abstract class ChestMenu implements AluminaMenu {

    private final Inventory inventory;
    private final Map<Integer, MenuItem> items;
    private final Map<String, MenuItem> byKey;

    /**
     * Using this constructor will allow you to create a new Chest Menu!
     *
     * @param title The title of the menu.
     * @param rows The amount of rows the menu should have.
     * @apiNote The amount of rows must be greater than 0 and less than or equal to 6.
     */
    public ChestMenu(@NotNull String title, int rows) {
        Preconditions.checkArgument(rows > 0, "Rows must be greater than 0.");
        Preconditions.checkArgument(rows <= 6, "Rows must be less than or equal to 6.");

        int size = rows * 9;

        this.inventory = Bukkit.createInventory(new ChestMenuHolder(this), size, ColorUtil.translate(title));
        this.items = Maps.newHashMap();
        this.byKey = Maps.newHashMap();
    }

    /**
     * This method will allow you to set the item in a slot.
     * @param slot The slot to set the item in.
     * @param item The item to set.
     * @param functionKey The function key to set.
     *                    When the function key is null, the item will not be functional.
     */
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
     * This method will allow you to add an item to the menu.
     * @param item The item to add.
     * @param functionKey The function key to set.
     */
    @Override
    public void addItem(@NotNull ItemStack item, @Nullable String functionKey) {
        int available = -1;
        for (int i = 0; i < inventory.getSize(); i++) {
            MenuItem menuItem = this.items.get(i);
            if (menuItem == null) {
                available = i;
                break;
            }
        }

        if (available == -1) return;

        setItem(available, item, functionKey);
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

    /**
     * This method will allow you to clear a slot.
     * @param slot The slot to clear.
     */
    @Override
    public void clearSlot(int slot) {
        inventory.clear(slot);
    }

    /**
     * This method will allow you to open the menu for a player.
     * @param player The player to open the menu for.
     */
    @Override
    public void open(@NotNull Player player) {
        items.forEach((index, item) -> inventory.setItem(index, item.item()));

        player.openInventory(inventory);
    }

    @Override
    public void onClick(@NotNull Player player, @NotNull InventoryClickEvent event) {
        // Override this method to handle clicks.
    }

    @Override
    public void onFunctionClick(@NotNull Player player, @NotNull MenuItem item, @NotNull InventoryClickEvent event) {
        // Override this method to handle function clicks.
    }

    @Override
    public void onClose(@NotNull Player player, @NotNull InventoryCloseEvent event) {
        // Override this method to handle close.
    }

    @Override
    public void onOpen(@NotNull Player player, @NotNull InventoryOpenEvent event) {
        // Override this method to handle open.
    }

    /**
     * Get all menu items.
     * @return The menu items.
     */
    public Map<Integer, MenuItem> getItems() {
        return items;
    }

    /**
     * Get a menu item by function key.
     * @param key The function key.
     * @return The menu item.
     */
    @Nullable
    public MenuItem byKey(@NotNull String key) {
        return byKey.getOrDefault(key, null);
    }
}
