/*
 *  MIT License
 *
 * Copyright (C) 2024 Negative Games
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

package me.joehosten.hypelib.builder;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import me.joehosten.hypelib.util.MiniMessageUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.CheckReturnValue;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * Represents a builder utility for {@link ItemStack} to make item creation easier for the developer.
 */
public class ItemBuilder {

    // This is required so item names will not have italics by default!
    private static final MiniMessage mm = MiniMessage.builder().postProcessor(component -> component.decoration(TextDecoration.ITALIC, false)).build();

    private final ItemStack item;
    private final ItemMeta meta;

    /**
     * Creates a new {@link ItemBuilder} instance from an existing {@link ItemStack}.
     * @param item The item to create the builder from.
     */
    public ItemBuilder(@NotNull final ItemStack item) {
        Preconditions.checkNotNull(item, "Item cannot be null!");

        this.item = item.clone();
        this.meta = this.item.getItemMeta();

        Preconditions.checkNotNull(this.meta, "ItemMeta cannot be null!");
    }

    /**
     * Creates a new {@link ItemBuilder} instance from an existing {@link ItemBuilder}.
     * @param builder The builder to create the builder from.
     */
    public ItemBuilder(@NotNull final ItemBuilder builder) {
        Preconditions.checkNotNull(builder, "ItemBuilder cannot be null!");

        this.item = builder.item.clone();
        this.meta = builder.meta.clone();
    }

    /**
     * Creates a new {@link ItemBuilder} instance from the provided {@link Material}.
     * @param material The material to create the builder from.
     */
    public ItemBuilder(@NotNull final Material material) {
        this(new ItemStack(material));
    }

    /**
     * Creates a new {@link ItemBuilder} instance from the provided {@link Material} and amount.
     * @param material The material to create the builder from.
     * @param amount The amount of the item.
     */
    public ItemBuilder(@NotNull final Material material, final int amount) {
        this(new ItemStack(material, amount));
    }

    /**
     * Set the display name of the item.
     * @param text The text to set the display name to.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder setName(@NotNull final String text) {
        Preconditions.checkNotNull(text, "Text cannot be null!");

        this.meta.displayName(MiniMessageUtil.translate(text, mm));
        return this;
    }

    /**
     * Set the display name of the item.
     * @param component The component to set the display name to.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder setName(@NotNull final Component component) {
        Preconditions.checkNotNull(component, "Component cannot be null!");

        this.meta.displayName(component);
        return this;
    }

    /**
     * Replace a placeholder in the display name with a replacement.
     * @param placeholder The placeholder to replace.
     * @param replacement The replacement for the placeholder.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder replaceName(@NotNull final String placeholder, @NotNull final String replacement) {
        Preconditions.checkNotNull(placeholder, "Placeholder cannot be null!");
        Preconditions.checkNotNull(replacement, "Replacement cannot be null!");

        Component component = this.meta.displayName();
        if (component == null) return this;

        Component modified = component.replaceText(TextReplacementConfig.builder().matchLiteral(placeholder).replacement(replacement).build());
        this.meta.displayName(modified);
        return this;
    }

    /**
     * Replace a placeholder in the display name with a replacement.
     * @param placeholder The placeholder to replace.
     * @param replacement The replacement for the placeholder.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder replaceName(@NotNull final String placeholder, @NotNull final Component replacement) {
        Preconditions.checkNotNull(placeholder, "Placeholder cannot be null!");
        Preconditions.checkNotNull(replacement, "Replacement cannot be null!");

        Component component = this.meta.displayName();
        if (component == null) return this;

        Component modified = component.replaceText(TextReplacementConfig.builder().matchLiteral(placeholder).replacement(replacement).build());
        this.meta.displayName(modified);
        return this;
    }

    /**
     * Set the lore of the item.
     * @param text The text to set the lore to.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder setLore(@NotNull final String... text) {
        Preconditions.checkNotNull(text, "Text cannot be null!");
        Preconditions.checkArgument(text.length > 0, "Text cannot be empty!");

        List<Component> components = Arrays.stream(text).map(s -> MiniMessageUtil.translate(s, mm)).collect(Collectors.toList());
        this.meta.lore(components);
        return this;
    }

    /**
     * Set the lore of the item.
     * @param components The components to set the lore to.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder setLore(@NotNull final Component... components) {
        Preconditions.checkNotNull(components, "Components cannot be null!");
        Preconditions.checkArgument(components.length > 0, "Components cannot be empty!");

        this.meta.lore(Arrays.asList(components));
        return this;
    }

    /**
     * Set the lore of the item.
     * @param text The text to set the lore to.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder setLore(@NotNull final List<String> text) {
        Preconditions.checkNotNull(text, "Text cannot be null!");
        Preconditions.checkArgument(!text.isEmpty(), "Text cannot be empty!");

        List<Component> components = text.stream().map(s -> MiniMessageUtil.translate(s, mm)).collect(Collectors.toList());
        this.meta.lore(components);
        return this;
    }

    /**
     * Set the lore of the item.
     * @param components The components to set the lore to.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder setLore(@NotNull final Collection<Component> components) {
        Preconditions.checkNotNull(components, "Components cannot be null!");
        Preconditions.checkArgument(!components.isEmpty(), "Components cannot be empty!");

        this.meta.lore(components.stream().toList());
        return this;
    }

    /**
     * Add a single line of lore to the item.
     * @param text The text to add to the lore.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder addLoreLine(@NotNull final String text) {
        Preconditions.checkNotNull(text, "Text cannot be null!");

        List<Component> lore = this.meta.lore();
        if (lore == null) lore = Lists.newArrayList();

        lore.add(MiniMessageUtil.translate(text, mm));
        this.meta.lore(lore);

        return this;
    }

    /**
     * Add a single line of lore to the item.
     * @param component The component to add to the lore.
     * @return  The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder addLoreLine(@NotNull final Component component) {
        Preconditions.checkNotNull(component, "Component cannot be null!");

        List<Component> lore = this.meta.lore();
        if (lore == null) lore = Lists.newArrayList();

        lore.add(component);
        this.meta.lore(lore);

        return this;
    }

    /**
     * Add multiple lines of lore to the item.
     * @param text The text to add to the lore.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder addLoreLines(@NotNull final String... text) {
        Preconditions.checkNotNull(text, "Text cannot be null!");
        Preconditions.checkArgument(text.length > 0, "Text cannot be empty!");

        List<Component> lore = this.meta.lore();
        if (lore == null) lore = Lists.newArrayList();

        List<Component> components = Arrays.stream(text).map(s -> MiniMessageUtil.translate(s, mm)).collect(Collectors.toList());
        lore.addAll(components);

        this.meta.lore(lore);

        return this;
    }

    /**
     * Add multiple lines of lore to the item.
     * @param components The components to add to the lore.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder addLoreLines(@NotNull Component... components) {
        Preconditions.checkNotNull(components, "Components cannot be null!");
        Preconditions.checkArgument(components.length > 0, "Components cannot be empty!");

        List<Component> lore = this.meta.lore();
        if (lore == null) lore = Lists.newArrayList();

        lore.addAll(Arrays.asList(components));

        this.meta.lore(lore);
        return this;
    }

    /**
     * Add multiple lines of lore to the item.
     * @param text The text to add to the lore.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder addLoreLines(@NotNull final List<String> text) {
        Preconditions.checkNotNull(text, "Text cannot be null!");
        Preconditions.checkArgument(!text.isEmpty(), "Text cannot be empty!");

        List<Component> lore = this.meta.lore();
        if (lore == null) lore = Lists.newArrayList();

        List<Component> components = text.stream().map(s -> MiniMessageUtil.translate(s, mm)).toList();
        lore.addAll(components);

        this.meta.lore(lore);
        return this;
    }

    @CheckReturnValue
    public ItemBuilder addLoreLines(@NotNull final Collection<Component> components) {
        Preconditions.checkNotNull(components, "Components cannot be null!");
        Preconditions.checkArgument(!components.isEmpty(), "Components cannot be empty!");

        List<Component> lore = this.meta.lore();
        if (lore == null) lore = Lists.newArrayList();

        lore.addAll(components);
        this.meta.lore(lore);
        return this;
    }

    /**
     * Replace the lore of the item.
     * @param function The function to replace the lore.
     * @return The current instance of the builder.
     * @deprecated Please ise {@link #replaceLore(String, String)} instead.
     */
    @Deprecated(since = "2.0.0", forRemoval = true)
    @CheckReturnValue
    public ItemBuilder replaceLore(@NotNull final UnaryOperator<String> function) {
        Preconditions.checkNotNull(function, "Function cannot be null!");

        List<String> lore = this.meta.getLore();
        if (lore == null) lore = Lists.newArrayList();

        lore.replaceAll(function);

        this.meta.setLore(lore);
        return this;
    }

    /**
     * Replace a single line of lore.
     * @param placeholder The placeholder to replace.
     * @param replacement The replacement for the placeholder.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder replaceLore(@NotNull final String placeholder, @NotNull final String replacement) {
        Preconditions.checkNotNull(placeholder, "Placeholder cannot be null!");
        Preconditions.checkNotNull(replacement, "Replacement cannot be null!");

        List<Component> lore = this.meta.lore();
        if (lore == null) lore = Lists.newArrayList();

        List<Component> modified = lore.stream()
                .map(component -> component.replaceText(TextReplacementConfig.builder().matchLiteral(placeholder).replacement(replacement).build()))
                .collect(Collectors.toList());

        this.meta.lore(modified);
        return this;
    }

    /**
     * Replace a single line of lore.
     * @param placeholder The placeholder to replace.
     * @param replacement The replacement for the placeholder.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder replaceLore(@NotNull final String placeholder, @NotNull final Component replacement) {
        Preconditions.checkNotNull(placeholder, "Placeholder cannot be null!");
        Preconditions.checkNotNull(replacement, "Replacement cannot be null!");

        List<Component> lore = this.meta.lore();
        if (lore == null) lore = Lists.newArrayList();

        List<Component> modified = lore.stream()
                .map(component -> component.replaceText(TextReplacementConfig.builder().matchLiteral(placeholder).replacement(replacement).build()))
                .collect(Collectors.toList());

        this.meta.lore(modified);
        return this;
    }

    /**
     * Replace a single line of lore with multiple lines.
     * @param placeholder The placeholder to replace.
     * @param replacement The replacement for the placeholder.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder replaceLore(@NotNull String placeholder, @NotNull List<String> replacement) {
        return replaceLore(placeholder, replacement.stream().map(s -> MiniMessageUtil.translate(s, mm)).map(component -> (TextComponent) component).toList());
    }

    /**
     * Replace a single line of lore with multiple lines.
     * @param placeholder The placeholder to replace.
     * @param replacement The replacement for the placeholder.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder replaceLore(@NotNull final String placeholder, @NotNull final Collection<TextComponent> replacement) {
        Preconditions.checkNotNull(placeholder, "Placeholder cannot be null!");
        Preconditions.checkNotNull(replacement, "Replacement cannot be null!");

        List<TextComponent> lore = Objects.requireNonNull(this.meta.lore()).stream()
                .filter(component -> component instanceof TextComponent)
                .map(component -> (TextComponent) component)
                .toList();

        List<TextComponent> components = Lists.newArrayList();

        for (TextComponent component : lore) {
            if (MiniMessageUtil.searchComponent(component, placeholder)) {
                components.addAll(replacement);
                continue;
            }

            components.add(component);
        }

        this.meta.lore(components);
        return this;
    }

    /**
     * Add an enchantment to the item.
     * @param enchantment The enchantment to add.
     * @param level The level of the enchantment.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder addEnchantment(@NotNull final Enchantment enchantment, final int level) {
        Preconditions.checkNotNull(enchantment, "Enchantment cannot be null!");
        Preconditions.checkArgument(level > 0, "Level must be greater than 0!");

        this.meta.addEnchant(enchantment, level, true);
        return this;
    }

    /**
     * Remove an enchantment from the item.
     * @param enchantment The enchantment to remove.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder removeEnchantment(@NotNull final Enchantment enchantment) {
        Preconditions.checkNotNull(enchantment, "Enchantment cannot be null!");

        this.meta.removeEnchant(enchantment);
        return this;
    }

    /**
     * Set the item to be unbreakable.
     * @param unbreakable Whether the item should be unbreakable.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder setUnbreakable(final boolean unbreakable) {
        this.meta.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Add an item flag to the item.
     * @param flags The flags to add.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder addItemFlags(@NotNull final ItemFlag... flags) {
        Preconditions.checkNotNull(flags, "Flags cannot be null!");
        Preconditions.checkArgument(flags.length > 0, "Flags cannot be empty!");

        this.meta.addItemFlags(flags);
        return this;
    }

    /**
     * Remove an item flag from the item.
     * @param flags The flags to remove.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder removeItemFlags(@NotNull final ItemFlag... flags) {
        Preconditions.checkNotNull(flags, "Flags cannot be null!");
        Preconditions.checkArgument(flags.length > 0, "Flags cannot be empty!");

        this.meta.removeItemFlags(flags);
        return this;
    }

    /**
     * Set the skull texture of the item.
     * @param player The player to set the skull texture to.
     * @return The current instance of the builder.
     * @throws ClassCastException If the item is not a skull.
     */
    @CheckReturnValue
    public ItemBuilder setSkullOwner(@NotNull final OfflinePlayer player) {
        Preconditions.checkNotNull(player, "Player cannot be null!");

        SkullMeta skullMeta = (SkullMeta) this.meta;
        skullMeta.setOwningPlayer(player);
        return this;
    }

    /**
     * Set the skull texture of the item.
     * @param profile The profile to set the skull texture to.
     * @return The current instance of the builder.
     * @throws ClassCastException If the item is not a skull.
     */
    @CheckReturnValue
    public ItemBuilder setSkullOwner(@NotNull final PlayerProfile profile) {
        Preconditions.checkNotNull(profile, "Profile cannot be null!");
        SkullMeta skullMeta = (SkullMeta) this.meta;
        skullMeta.setPlayerProfile(profile);
        return this;
    }

    /**
     * Set the custom model data of the item.
     * @param data The custom model data to set.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder setCustomModelData(@NotNull final Integer data) {
        this.meta.setCustomModelData(data);
        return this;
    }

    /**
     * Set the color of the item.
     * @param color The color to set.
     * @return The current instance of the builder.
     * @throws ClassCastException If the item is not a leather armor piece.
     */
    @CheckReturnValue
    public ItemBuilder setLeatherColor(@NotNull final Color color) {
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) this.meta;
        leatherArmorMeta.setColor(color);
        return this;
    }

    /**
     * Apply custom persistent data to the item.
     * @param function The function to apply the persistent data.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder applyPersistentData(@NotNull final Consumer<PersistentDataContainer> function) {
        Preconditions.checkNotNull(function, "Function cannot be null!");

        function.accept(this.meta.getPersistentDataContainer());
        return this;
    }

    /**
     * Add custom persistent data to the item.
     * @param key The key to add the data to.
     * @param type The type of the data.
     * @param value The value of the data.
     * @return The current instance of the builder.
     * @param <T> The type of the data.
     * @param <V> The value of the data.
     */
    @CheckReturnValue
    public <T extends PersistentDataType<V, V>, V> ItemBuilder addNamespacedKey(@NotNull NamespacedKey key, @NotNull T type, @NotNull V value) {
        this.meta.getPersistentDataContainer().set(key, type, value);
        return this;
    }

    /**
     * Add an attribute modifier to the item.
     * @param attribute The attribute to add the modifier to.
     * @param modifier The modifier to add.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder addAttributeModifier(@NotNull Attribute attribute, @NotNull AttributeModifier modifier) {
        Preconditions.checkNotNull(attribute, "Attribute cannot be null!");
        Preconditions.checkNotNull(modifier, "Modifier cannot be null!");

        this.meta.addAttributeModifier(attribute, modifier);
        return this;
    }

    /**
     * Remove an attribute modifier from the item.
     * @param attribute The attribute to remove the modifier from.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder removeAttributeModifier(@NotNull final Attribute attribute) {
        Preconditions.checkNotNull(attribute, "Attribute cannot be null!");

        this.meta.removeAttributeModifier(attribute);
        return this;
    }

    /**
     * Remove an attribute modifier from the item.
     * @param attribute The attribute to remove the modifier from.
     * @param modifier The modifier to remove.
     * @return The current instance of the builder.
     */
    @CheckReturnValue
    public ItemBuilder removeAttributeModifier(@NotNull final Attribute attribute, @NotNull final AttributeModifier modifier) {
        Preconditions.checkNotNull(attribute, "Attribute cannot be null!");
        Preconditions.checkNotNull(modifier, "Modifier cannot be null!");

        this.meta.removeAttributeModifier(attribute, modifier);
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

    /**
     * Get the {@link ItemMeta} of the item.
     *
     * @return The {@link ItemMeta} object representing the metadata of the item.
     */
    @NotNull
    public ItemMeta meta() {
        return this.meta;
    }

    /**
     * Returns the ItemStack associated with this ItemBuilder.
     *
     * @return The ItemStack.
     */
    @NotNull
    public ItemStack item() {
        return this.item;
    }

}
