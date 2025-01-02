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

package me.joehosten.hypelib.position;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

/**
 * The Locatable interface provides methods to get and set the location of an object.
 */
public interface Locatable {

    /**
     * Retrieves the location of the object.
     *
     * @return The location of the object.
     */
    @NotNull
    Location getLocation();

    /**
     * Sets the location of the object.
     *
     * @param location the location to set
     * @throws UnsupportedOperationException if the object does not allow setting its location
     */
    default void setLocation(@NotNull Location location) {
        throw new UnsupportedOperationException("This object does not allow setting its location");
    }

}
