package games.negative.alumina.task;

import games.negative.alumina.AluminaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;


/**
 * The Tasks interface provides static methods to schedule tasks in a Bukkit server environment.
 */
public interface Tasks {

    /**
     * Runs the given Runnable on the main thread of the Bukkit server.
     *
     * @param runnable The Runnable to be executed.
     * @return The BukkitTask representing the scheduled task.
     * @throws NullPointerException if the runnable is null.
     */
    static BukkitTask run(@NotNull Runnable runnable) {
        return Bukkit.getScheduler().runTask(AluminaPlugin.getAluminaInstance(), runnable);
    }

    /**
     * Runs the given Runnable on the main thread after a delay.
     *
     * @param runnable The Runnable to be executed.
     * @param delay The delay in ticks before the Runnable is executed.
     * @return The BukkitTask representing the scheduled task.
     * @throws NullPointerException if the runnable is null.
     */
    static BukkitTask run(@NotNull Runnable runnable, long delay) {
        return Bukkit.getScheduler().runTaskLater(AluminaPlugin.getAluminaInstance(), runnable, delay);
    }

    /**
     * Runs the given Runnable on the main thread after a delay and at a specific interval.
     *
     * @param runnable The Runnable to be executed.
     * @param delay    The delay in ticks before the Runnable is first executed.
     * @param period   The interval in ticks between subsequent executions of the Runnable.
     * @return The BukkitTask representing the scheduled task.
     * @throws NullPointerException if the runnable is null.
     */
    static BukkitTask run(@NotNull Runnable runnable, long delay, long period) {
        return Bukkit.getScheduler().runTaskTimer(AluminaPlugin.getAluminaInstance(), runnable, delay, period);
    }

    /**
     * Runs the given Runnable asynchronously on a separate thread.
     *
     * @param runnable The Runnable to be executed.
     * @return The BukkitTask representing the scheduled task.
     * @throws NullPointerException if the runnable is null.
     */
    static BukkitTask async(@NotNull Runnable runnable) {
        return Bukkit.getScheduler().runTaskAsynchronously(AluminaPlugin.getAluminaInstance(), runnable);
    }

    /**
     * Runs the given Runnable asynchronously on a separate thread after a delay.
     *
     * @param runnable The Runnable to be executed.
     * @param delay The delay in ticks before the Runnable is executed.
     * @return The BukkitTask representing the scheduled task.
     * @throws NullPointerException if the runnable is null.
     */
    static BukkitTask async(@NotNull Runnable runnable, long delay) {
        return Bukkit.getScheduler().runTaskLaterAsynchronously(AluminaPlugin.getAluminaInstance(), runnable, delay);
    }

    /**
     * Runs the given Runnable asynchronously on a separate thread with a delay and at a specific interval.
     *
     * @param runnable The Runnable to be executed.
     * @param delay The delay in ticks before the Runnable is executed.
     * @param period The interval in ticks between subsequent executions of the Runnable.
     * @return The BukkitTask representing the scheduled task.
     * @throws NullPointerException if the runnable is null.
     */
    static BukkitTask async(@NotNull Runnable runnable, long delay, long period) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(AluminaPlugin.getAluminaInstance(), runnable, delay, period);
    }

    /**
     * Executes the given Callable on a separate thread and returns a CompletableFuture that represents the result.
     *
     * @param callable The Callable to be executed.
     * @param <T> The type of the result.
     * @return A CompletableFuture that will complete with the result of the Callable.
     * @throws NullPointerException if the callable is null.
     */
    static <T> CompletableFuture<T> await(@NotNull Callable<T> callable) {
        CompletableFuture<T> future = new CompletableFuture<>();
        async(() -> {
            try {
                future.complete(callable.call());
            } catch (Exception exception) {
                future.completeExceptionally(exception);
            }
        });
        return future;
    }
}
