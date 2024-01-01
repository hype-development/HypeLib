package games.negative.alumina.model.future;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * A task to completed in the future with a result.
 * Exactly the same as {@link CompletableFuture} but using Bukkit Threads.
 * @param <T> The type of the result.
 */
public interface BukkitFuture<T> {

    /**
     * Supply a value to the future.
     * @param supplier The supplier to supply the value.
     * @return The future.
     */
    BukkitFuture<T> supply(@NotNull Supplier<T> supplier);

    /**
     * Supply a value to the future asynchronously.
     * @param supplier The supplier to supply the value.
     * @return The future.
     */
    BukkitFuture<T> supplyAsync(@NotNull Supplier<T> supplier);

    /**
     * Supply a value to the future asynchronously.
     * @param task The task to supply the value.
     */
    void whenComplete(@Nullable Consumer<T> task);

    /**
     * Supply a value to the future asynchronously.
     * @param task The task to supply the value.
     */
    void whenCompleteAsync(@Nullable Consumer<T> task);

    /**
     * Cancel the future task.
     */
    void cancel();
}
