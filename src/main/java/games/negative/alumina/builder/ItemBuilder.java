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

package games.negative.alumina.builder;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import games.negative.alumina.util.ColorUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Represents a builder utility for {@link ItemStack} to make item creation easier for the developer.
 */
public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;

    /**
     * Creates a new {@link ItemBuilder} instance from an existing {@link ItemStack}.
     * @param item The item to create the builder from.
     */
    public ItemBuilder(@NotNull ItemStack item) {
        this.item = item;
        this.meta = item.getItemMeta();

        Preconditions.checkNotNull(this.meta, "ItemMeta cannot be null!");
    }

    /**
     * Creates a new {@link ItemBuilder} instance from an existing {@link ItemBuilder}.
     * @param builder The builder to create the builder from.
     */
    public ItemBuilder(@NotNull ItemBuilder builder) {
        this.item = builder.item;
        this.meta = builder.meta;
    }

    /**
     * Creates a new {@link ItemBuilder} instance from the provided {@link Material}.
     * @param material The material to create the builder from.
     */
    public ItemBuilder(@NotNull Material material) {
        this(new ItemStack(material));
    }

    /**
     * Creates a new {@link ItemBuilder} instance from the provided {@link Material} and amount.
     * @param material The material to create the builder from.
     * @param amount The amount of the item.
     */
    public ItemBuilder(@NotNull Material material, int amount) {
        this(new ItemStack(material, amount));
    }

    /**
     * Set the display name of the item.
     * @param text The text to set the display name to.
     * @return The current instance of the builder.
     */
    @NotNull
    public ItemBuilder setName(@NotNull String text) {
        this.meta.setDisplayName(ColorUtil.translate(text));
        return this;
    }

    /**
     * Set the lore of the item.
     * @param text The text to set the lore to.
     * @return The current instance of the builder.
     */
    @NotNull
    public ItemBuilder setLore(@NotNull String... text) {
        List<String> parsed = Lists.newArrayList();

        for (String line : text) {
            parsed.add(ColorUtil.translate(line));
        }

        this.meta.setLore(parsed);
        return this;
    }

    /**
     * Set the lore of the item.
     * @param text The text to set the lore to.
     * @return The current instance of the builder.
     */
    @NotNull
    public ItemBuilder setLore(@NotNull List<String> text) {
        List<String> parsed = Lists.newArrayList();

        for (String line : text) {
            parsed.add(ColorUtil.translate(line));
        }

        this.meta.setLore(parsed);
        return this;
    }

    /**
     * Add a single line of lore to the item.
     * @param text The text to add to the lore.
     * @return The current instance of the builder.
     */
    @NotNull
    public ItemBuilder addLoreLine(@NotNull String text) {
        List<String> lore = this.meta.getLore();
        if (lore == null) lore = Lists.newArrayList();

        lore.add(ColorUtil.translate(text));
        this.meta.setLore(lore);

        return this;
    }

    /**
     * Add multiple lines of lore to the item.
     * @param text The text to add to the lore.
     * @return The current instance of the builder.
     */
    @NotNull
    public ItemBuilder addLoreLines(@NotNull String... text) {
        List<String> lore = this.meta.getLore();
        if (lore == null) lore = Lists.newArrayList();

        for (String line : text) {
            lore.add(ColorUtil.translate(line));
        }

        this.meta.setLore(lore);

        return this;
    }

    /**
     * Add multiple lines of lore to the item.
     * @param text The text to add to the lore.
     * @return The current instance of the builder.
     */
    @NotNull
    public ItemBuilder addLoreLines(@NotNull List<String> text) {
        List<String> lore = this.meta.getLore();
        if (lore == null) lore = Lists.newArrayList();

        for (String line : text) {
            lore.add(ColorUtil.translate(line));
        }

        this.meta.setLore(lore);

        return this;
    }

    /**
     * Construct the {@link ItemStack} from the builder.
     * @return The constructed {@link ItemStack}.
     */
    @NotNull
    public ItemStack build() {
        this.item.setItemMeta(this.meta);
        return this.item;
    }

}
