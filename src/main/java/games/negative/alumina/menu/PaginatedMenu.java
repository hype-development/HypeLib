package games.negative.alumina.menu;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import games.negative.alumina.AluminaPlugin;
import games.negative.alumina.util.ColorUtil;
import games.negative.alumina.util.MathUtil;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class PaginatedMenu implements InteractiveMenu {

    private static final int MIN_ROWS = 1;
    private static final int MAX_ROWS = 6;

    private static final NamespacedKey FUNCTION = new NamespacedKey(AluminaPlugin.getAluminaInstance(), "chest-menu-function");

    private String title = "Chest Menu";
    private int rows = 1;

    private final Set<MenuButton> buttons;
    private final Set<MenuButton> listing;

    protected Inventory inventory;

    public PaginatedMenu(@NotNull String title, int rows) {
        Preconditions.checkNotNull(title, "Title cannot be null");
        Preconditions.checkArgument(MathUtil.between(rows, MIN_ROWS, MAX_ROWS), "Rows must be between " + MIN_ROWS + " and " + MAX_ROWS);

        this.title = title;
        this.rows = rows;

        this.buttons = Sets.newLinkedHashSet();
        this.listing = Sets.newLinkedHashSet();

        this.inventory = Bukkit.createInventory(null, rows * 9, ColorUtil.translate(title));
    }

    public PaginatedMenu() {
        this.buttons = Sets.newLinkedHashSet();
        this.listing = Sets.newLinkedHashSet();
    }

    @Override
    public void onOpen(@NotNull Player player, @NotNull InventoryOpenEvent event) {

    }

    @Override
    public void onClose(@NotNull Player player, @NotNull InventoryCloseEvent event) {

    }

    @Override
    public void onClick(@NotNull Player player, @NotNull InventoryClickEvent event) {

    }

    public <T extends Iterable<K>, K> Collection<MenuButton> generateButtons(@NotNull T collection, @NotNull Function<K, MenuButton> function) {
        Preconditions.checkNotNull(collection, "Collection cannot be null");
        Preconditions.checkNotNull(function, "Function cannot be null");

        for (K key : collection) {
            MenuButton button = function.apply(key);
            buttons.add(button);
        }

        return buttons;
    }
}
