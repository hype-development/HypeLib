package games.negative.alumina.model.future;

import games.negative.alumina.util.Tasks;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BukkitCompletableFuture<T> implements BukkitFuture<T> {

    private final AtomicReference<T> reference = new AtomicReference<>();
    private Consumer<T> task;
    private boolean async = false;
    private final BukkitTask bukkitTask;

    public BukkitCompletableFuture() {
        this.bukkitTask = Tasks.async(() -> {
            T value = reference.get();
            if (value == null || task == null) return;

            if (async) {
                task.accept(value);
            } else {
                Tasks.run(() -> task.accept(value));
            }
        }, 0, 1);
    }

    @Override
    public BukkitFuture<T> supply(@NotNull Supplier<T> supplier) {
        Tasks.run(() -> this.reference.set(supplier.get()));
        return this;
    }

    @Override
    public BukkitFuture<T> supplyAsync(@NotNull Supplier<T> supplier) {
        Tasks.async(() -> this.reference.set(supplier.get()));
        return this;
    }

    @Override
    public void whenComplete(@Nullable Consumer<T> task) {
        this.task = task;
        this.async = false;
    }

    @Override
    public void whenCompleteAsync(@Nullable Consumer<T> task) {
        this.task = task;
        async = true;
    }

    @Override
    public void cancel() {
        this.reference.set(null);
        this.bukkitTask.cancel();
    }
}
