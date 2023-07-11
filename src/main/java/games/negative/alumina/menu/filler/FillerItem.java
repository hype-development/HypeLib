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

package games.negative.alumina.menu.filler;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents an item that is used to fill empty spaces in a menu.
 */
public class FillerItem extends ItemStack {

    public static final FillerItem BLACK = new FillerItem(Material.BLACK_STAINED_GLASS_PANE);
    public static final FillerItem WHITE = new FillerItem(Material.WHITE_STAINED_GLASS_PANE);
    public static final FillerItem ORANGE = new FillerItem(Material.ORANGE_STAINED_GLASS_PANE);
    public static final FillerItem MAGENTA = new FillerItem(Material.MAGENTA_STAINED_GLASS_PANE);
    public static final FillerItem LIGHT_BLUE = new FillerItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
    public static final FillerItem YELLOW = new FillerItem(Material.YELLOW_STAINED_GLASS_PANE);
    public static final FillerItem LIME = new FillerItem(Material.LIME_STAINED_GLASS_PANE);
    public static final FillerItem PINK = new FillerItem(Material.PINK_STAINED_GLASS_PANE);
    public static final FillerItem GRAY = new FillerItem(Material.GRAY_STAINED_GLASS_PANE);
    public static final FillerItem LIGHT_GRAY = new FillerItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
    public static final FillerItem CYAN = new FillerItem(Material.CYAN_STAINED_GLASS_PANE);
    public static final FillerItem PURPLE = new FillerItem(Material.PURPLE_STAINED_GLASS_PANE);
    public static final FillerItem BLUE = new FillerItem(Material.BLUE_STAINED_GLASS_PANE);
    public static final FillerItem BROWN = new FillerItem(Material.BROWN_STAINED_GLASS_PANE);
    public static final FillerItem GREEN = new FillerItem(Material.GREEN_STAINED_GLASS_PANE);
    public static final FillerItem RED = new FillerItem(Material.RED_STAINED_GLASS_PANE);

    public FillerItem(@NotNull Material type) {
        super(type);

        ItemMeta meta = Bukkit.getItemFactory().getItemMeta(type);
        if (meta == null) throw new IllegalStateException("ItemMeta is null");

        meta.setDisplayName(" ");
        setItemMeta(meta);
    }

}
