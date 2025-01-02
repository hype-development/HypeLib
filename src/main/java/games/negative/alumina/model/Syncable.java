/*
 *  MIT License
 *
 * Copyright (C) 2025 Negative Games
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


package games.negative.alumina.model;

/**
 * The Syncable interface represents a class that can be synced.
 */
public interface Syncable {

    /**
     * Synchronizes the data of the implementing class with the underlying data source.
     *
     * This method performs the necessary operations to sync the data of the implementing class with the underlying data source.
     * It updates the data in the data source to reflect the current state of the implementing class.
     *
     * Note that this method does not return any value.
     *
     * Usage example:
     *
     * Syncable syncable = new MySyncableClass();
     * syncable.sync();
     *
     * In the above example, the sync() method is called on an instance of a class that implements the Syncable interface.
     * This triggers the synchronization process and updates the data in the data source accordingly.
     */
    void sync();

}
