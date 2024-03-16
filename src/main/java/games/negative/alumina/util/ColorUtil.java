/*
 *
 *  *  MIT License
 *  *
 *  * Copyright (C) 2024 Negative Games
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

package games.negative.alumina.util;

import com.google.common.base.Preconditions;
import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a utility class which contains methods for color manipulation.
 * @deprecated This class is deprecated and will be removed in a future update.
 */
@Deprecated(since = "2.0.0" ,forRemoval = true)
public class ColorUtil {

    /*
     * This pattern is used to match hex colors.
     *
     * Example &[FFFFFF] would be matched as #FFFFFF
     */
    private static final Pattern PATTERN = Pattern.compile("&\\[([A-Fa-f0-9]{6})]");

    /**
     * Translates a string with the basic color code and the hex color code.
     *
     * @param input The string to translate.
     * @return The translated string.
     */
    public static String translate(@NotNull String input, @Nullable Pattern pattern) {
        Preconditions.checkNotNull(input, "Input cannot be null.");

        if (pattern == null) pattern = PATTERN;

        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String color = input.substring(matcher.start(), matcher.end());
            input = input.replace(color, String.valueOf(ChatColor.of(color)));
            matcher = pattern.matcher(input);
        }
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    /**
     * Translates a string with the basic color code and the hex color code.
     * @param input The string to translate.
     * @return The translated string.
     */
    public static String translate(@NotNull String input) {
        return translate(input, null);
    }

    /**
     * Translates a list of strings with the basic color code and the hex color code.
     * @param input The list of strings to translate.
     * @param pattern The pattern to use.
     * @return The translated list of strings.
     */
    public static List<String> translate(@NotNull List<String> input, @Nullable Pattern pattern) {
        Preconditions.checkNotNull(input, "Input cannot be null.");

        if (pattern == null) pattern = PATTERN;

        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            input.set(i, translate(line, pattern));
        }
        return input;
    }

    /**
     * Translates a list of strings with the basic color code and the hex color code.
     * @param input The list of strings to translate.
     * @return The translated list of strings.
     */
    public static List<String> translate(@NotNull List<String> input) {
        return translate(input, null);
    }
}
