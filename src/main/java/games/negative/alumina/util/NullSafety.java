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
