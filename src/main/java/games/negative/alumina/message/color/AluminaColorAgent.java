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

package games.negative.alumina.message.color;

import com.google.common.base.Preconditions;
import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Alumina's default color agent.
 */
public class AluminaColorAgent implements ColorAgent {

    /*
     * This pattern is used to match hex colors.
     *
     * Example &[FFFFFF] would be matched as #FFFFFF
     */
    private final Pattern PATTERN = Pattern.compile("&\\[([A-Fa-f0-9]{6})]");

    /*
     * This is the fallback color agent.
     */
    private final ColorAgent fallback;

    public AluminaColorAgent() {
        this.fallback = new MinecraftColorAgent();
    }

    @Override
    public String translate(@NotNull String input) {
        Preconditions.checkNotNull(input, "input cannot be null!");

        Matcher matcher = PATTERN.matcher(input);
        while (matcher.find()) {
            String color = input.substring(matcher.start(), matcher.end());
            input = input.replace(color, String.valueOf(ChatColor.of(color)));
            matcher = PATTERN.matcher(input);
        }
        return fallback.translate(input);
    }
}
