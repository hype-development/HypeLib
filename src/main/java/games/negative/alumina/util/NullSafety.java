package games.negative.alumina.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * The NullSafety class provides methods for handling null values in a safe manner.
 */
public class NullSafety {

    /**
     * Retrieves the reference value if it is not null; otherwise, returns the provided value.
     *
     * @param ref the reference value to be checked
     * @param val the value to be returned if the reference is null
     * @param <T> the type of the values
     * @return the reference value if it is not null; otherwise, the provided value
     */
    @NotNull
    public static <T> T get(@Nullable T ref, @NotNull T val) {
        return ref == null ? val : ref;
    }

    /**
     * Retrieves a nullable reference, or returns a default value supplied by a supplier if the reference is null.
     *
     * @param ref      the nullable reference to retrieve
     * @param supplier the supplier to provide a default value if the reference is null
     * @param <T>      the type of the reference
     * @return the reference if not null, otherwise the default value supplied by the supplier
     * @throws NullPointerException if the supplier is null
     */
    @NotNull
    public static <T> T get(@Nullable T ref, @NotNull Supplier<T> supplier) {
        return ref == null ? supplier.get() : ref;
    }

}
