/*
 *
 *  *  MIT License
 *  *
 *  * Copyright (C) 2023 Negative Games
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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a number utility class used to handle numbers, parse them, etc.
 */
public class NumberUtil {

    /*
     * Format a number to a fancy format.
     */
    private static final DecimalFormat FANCY_FORMAT = new DecimalFormat("###,###,###,###,###.##");

    private static final String SUFFIXES = "kMBTQqSsOND";

    /**
     * Parse a number to a fancy format.
     *
     * @param number Number to parse
     * @return Parsed number
     */
    public static String decimalFormat(int number) {
        return FANCY_FORMAT.format(number);
    }

    /**
     * Parse a number to a fancy format.
     *
     * @param number Number to parse
     * @return Parsed number
     */
    public static String decimalFormat(long number) {
        return FANCY_FORMAT.format(number);
    }

    /**
     * Parse a number to a fancy format.
     *
     * @param number Number to parse
     * @return Parsed number
     */
    public static String decimalFormat(double number) {
        return FANCY_FORMAT.format(number);
    }

    /**
     * Parse a number to a fancy format.
     *
     * @param number Number to parse
     * @return Parsed number
     */
    public static String decimalFormat(float number) {
        return FANCY_FORMAT.format(number);
    }

    /**
     * Parse a number to a fancy format.
     *
     * @param number Number to parse
     * @return Parsed number
     */
    public static String decimalFormat(short number) {
        return FANCY_FORMAT.format(number);
    }

    /**
     * Parse a number to a fancy format.
     *
     * @param number Number to parse
     * @return Parsed number
     */
    public static String decimalFormat(byte number) {
        return FANCY_FORMAT.format(number);
    }

    /**
     * Parse a number to a fancy format.
     * @param number Number to parse
     * @return Parsed number
     */
    public static String decimalFormat(BigDecimal number) {
        return FANCY_FORMAT.format(number);
    }

    /**
     * This method will check if the provided text is a {@link Integer}.
     *
     * @param text Text to check
     * @return If the text is a {@link Integer}
     */
    public static boolean isInteger(final String text) {
        Preconditions.checkNotNull(text, "'text' cannot be null!");

        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method will get an {@link Integer} from the provided text.
     *
     * @param text Text to get the {@link Integer} from
     * @return {@link Integer} from the text
     * @throws NullPointerException If the text is not an {@link Integer}
     */
    public static Integer getInteger(final String text) {
        Preconditions.checkNotNull(text, "'text' cannot be null!");

        if (!isInteger(text)) return null;

        return Integer.parseInt(text);
    }

    /**
     * This method will check if the provided text is a {@link Long}.
     *
     * @param text Text to check
     * @return If the text is a {@link Long}
     */
    public static boolean isLong(final String text) {
        Preconditions.checkNotNull(text, "'text' cannot be null!");

        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method will get an {@link Long} from the provided text.
     *
     * @param text Text to get the {@link Long} from
     * @return {@link Long} from the text
     * @throws NullPointerException If the text is not an {@link Long}
     */
    public static Long getLong(final String text) {
        Preconditions.checkNotNull(text, "'text' cannot be null!");

        if (!isLong(text)) return null;

        return Long.parseLong(text);
    }

    /**
     * This method will check if the provided text is a {@link Double}.
     *
     * @param text Text to check
     * @return If the text is a {@link Double}
     */
    public static boolean isDouble(final String text) {
        Preconditions.checkNotNull(text, "'text' cannot be null!");

        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method will get an {@link Double} from the provided text.
     *
     * @param text Text to get the {@link Double} from
     * @return {@link Double} from the text
     * @throws NullPointerException If the text is not an {@link Double}
     */
    public static Double getDouble(final String text) {
        Preconditions.checkNotNull(text, "'text' cannot be null!");

        if (!isDouble(text)) return null;

        return Double.parseDouble(text);
    }

    /**
     * This method will check if the provided text is a {@link Float}.
     *
     * @param text Text to check
     * @return If the text is a {@link Float}
     */
    public static boolean isFloat(final String text) {
        Preconditions.checkNotNull(text, "'text' cannot be null!");

        try {
            Float.parseFloat(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method will get an {@link Float} from the provided text.
     *
     * @param text Text to get the {@link Float} from
     * @return {@link Float} from the text
     * @throws NullPointerException If the text is not an {@link Float}
     */
    public static Float getFloat(final String text) {
        Preconditions.checkNotNull(text, "'text' cannot be null!");

        if (!isFloat(text)) return null;

        return Float.parseFloat(text);
    }

    /**
     * This method will check if the provided text is a {@link Short}.
     *
     * @param text Text to check
     * @return If the text is a {@link Short}
     */
    public static boolean isShort(final String text) {
        Preconditions.checkNotNull(text, "'text' cannot be null!");

        try {
            Short.parseShort(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method will get an {@link Short} from the provided text.
     *
     * @param text Text to get the {@link Short} from
     * @return {@link Short} from the text
     * @throws NullPointerException If the text is not an {@link Short}
     */
    public static Short getShort(final String text) {
        Preconditions.checkNotNull(text, "'text' cannot be null!");

        if (!isShort(text)) return null;

        return Short.parseShort(text);
    }

    /**
     * This method will convert a number to a fancy version of
     * the provided number such as 1st, 2nd, 3rd, 4th, etc.
     *
     * @param number Number to convert
     * @return Fancy version of the number
     */
    public static String fancy(int number) {
        if (number % 100 >= 11 && number % 100 <= 13) {
            return decimalFormat(number) + "th";
        }

        return switch (number % 10) {
            case 1 -> decimalFormat(number) + "st";
            case 2 -> decimalFormat(number) + "nd";
            case 3 -> decimalFormat(number) + "rd";
            default -> decimalFormat(number) + "th";
        };
    }

    /**
     * This method will convert a number to a fancy version of
     * the provided number such as 1st, 2nd, 3rd, 4th, etc.
     *
     * @param number Number to convert
     * @return Fancy version of the number
     */
    public static String fancy(long number) {
        if (number % 100 >= 11 && number % 100 <= 13) {
            return decimalFormat(number) + "th";
        }

        return switch ((int) (number % 10)) {
            case 1 -> decimalFormat(number) + "st";
            case 2 -> decimalFormat(number) + "nd";
            case 3 -> decimalFormat(number) + "rd";
            default -> decimalFormat(number) + "th";
        };
    }

    /**
     * This method will convert a number to a fancy version of
     * the provided number such as 1st, 2nd, 3rd, 4th, etc.
     *
     * @param number Number to convert
     * @return Fancy version of the number
     */
    public static String fancy(double number) {
        if (number % 100 >= 11 && number % 100 <= 13) {
            return decimalFormat(number) + "th";
        }

        return switch ((int) (number % 10)) {
            case 1 -> decimalFormat(number) + "st";
            case 2 -> decimalFormat(number) + "nd";
            case 3 -> decimalFormat(number) + "rd";
            default -> decimalFormat(number) + "th";
        };
    }

    /**
     * This method will convert a number to a fancy version of
     * the provided number such as 1st, 2nd, 3rd, 4th, etc.
     *
     * @param number Number to convert
     * @return Fancy version of the number
     */
    public static String fancy(float number) {
        if (number % 100 >= 11 && number % 100 <= 13) {
            return number + "th";
        }

        return switch ((int) (number % 10)) {
            case 1 -> decimalFormat(number) + "st";
            case 2 -> decimalFormat(number) + "nd";
            case 3 -> decimalFormat(number) + "rd";
            default -> decimalFormat(number) + "th";
        };
    }

    /**
     * This method will convert a number to a fancy version of
     * the provided number such as 1st, 2nd, 3rd, 4th, etc.
     *
     * @param number Number to convert
     * @return Fancy version of the number
     */
    public static String fancy(short number) {
        if (number % 100 >= 11 && number % 100 <= 13) {
            return decimalFormat(number) + "th";
        }

        return switch (number % 10) {
            case 1 -> decimalFormat(number) + "st";
            case 2 -> decimalFormat(number) + "nd";
            case 3 -> decimalFormat(number) + "rd";
            default -> decimalFormat(number) + "th";
        };
    }

    /**
     * This method will convert a number to a fancy version of
     * the provided number such as 1st, 2nd, 3rd, 4th, etc.
     *
     * @param number Number to convert
     * @return Fancy version of the number
     */
    public static String fancy(byte number) {
        if (number % 100 >= 11 && number % 100 <= 13) {
            return decimalFormat(number) + "th";
        }

        return switch (number % 10) {
            case 1 -> decimalFormat(number) + "st";
            case 2 -> decimalFormat(number) + "nd";
            case 3 -> decimalFormat(number) + "rd";
            default -> decimalFormat(number) + "th";
        };
    }

    /**
     * Condenses a number into a shorter version using suffixes.
     * @param number Number to condense
     * @return Condensed number
     */
    public static String condense(int number) {
        return condense(number, null);
    }

    /**
     * Condenses a number into a shorter version using suffixes.
     * @param number Number to condense
     * @param set Set of suffixes to use
     * @return Condensed number
     */
    public static String condense(int number, final char[] set) {
        if (number < 1000) return String.valueOf(number); // Return the number itself if less than 1000.

        int exp = (int) (Math.log(number) / Math.log(1000));

        String suffixes = (set == null) ? SUFFIXES : new String(set);
        char suffix = suffixes.charAt(Math.min(exp - 1, suffixes.length() - 1));

        return String.format("%.1f%c", number / Math.pow(1000, exp), suffix);
    }

    /**
     * Condenses a number into a shorter version using suffixes.
     * @param number Number to condense
     * @return Condensed number
     */
    public static String condense(double number) {
        return condense(number, null);
    }

    /**
     * Condenses a number into a shorter version using suffixes.
     * @param number Number to condense
     * @param set Set of suffixes to use
     * @return Condensed number
     */
    public static String condense(double number, final char[] set) {
        if (number < 1000) return String.valueOf(number); // Return the number itself if less than 1000.

        int exp = (int) (Math.log(number) / Math.log(1000));

        String suffixes = (set == null) ? SUFFIXES : new String(set);
        char suffix = suffixes.charAt(Math.min(exp - 1, suffixes.length() - 1));

        return String.format("%.1f%c", number / Math.pow(1000, exp), suffix);
    }

    /**
     * Condenses a number into a shorter version using suffixes.
     * @param number Number to condense
     * @return Condensed number
     */
    public static String condense(long number) {
        return condense(number, null);
    }

    /**
     * Condenses a number into a shorter version using suffixes.
     * @param number Number to condense
     * @param set Set of suffixes to use
     * @return Condensed number
     */
    public static String condense(long number, final char[] set) {
        if (number < 1000) return String.valueOf(number); // Return the number itself if less than 1000.

        int exp = (int) (Math.log(number) / Math.log(1000));

        String suffixes = (set == null) ? SUFFIXES : new String(set);
        char suffix = suffixes.charAt(Math.min(exp - 1, suffixes.length() - 1));

        return String.format("%.1f%c", number / Math.pow(1000, exp), suffix);
    }

    /**
     * Condenses a number into a shorter version using suffixes.
     * @param number Number to condense
     * @return Condensed number
     */
    public static String condense(final BigDecimal number) {
        Preconditions.checkNotNull(number, "'number' cannot be null!");

        return condense(number, null);
    }

    /**
     * Condenses a number into a shorter version using suffixes.
     * @param number Number to condense
     * @param set Set of suffixes to use
     * @return Condensed number
     */
    public static String condense(final BigDecimal number, final char[] set) {
        Preconditions.checkNotNull(number, "'number' cannot be null!");

        BigDecimal thousand = BigDecimal.valueOf(1000);
        if (number.compareTo(thousand) < 0) {
            return number.stripTrailingZeros().toPlainString(); // Return the number itself if less than 1000.
        }

        int exp = (int) (Math.floor(Math.log10(number.doubleValue()) / 3));

        String suffixes = (set == null) ? SUFFIXES : new String(set);
        char suffix = suffixes.charAt(Math.min(exp - 1, suffixes.length() - 1));

        BigDecimal result = number.divide(thousand.pow(exp), 1, RoundingMode.HALF_UP);

        return String.format("%.1f%c", result, suffix);
    }

    /**
     * Simulates the result of the vanilla fortune enchantment.
     * @param item The item to simulate
     * @param tool The tool to simulate
     * @return The simulated result
     */
    public static int simulateFortune(final ItemStack tool, final ItemStack item) {
        Preconditions.checkNotNull(tool, "'tool' cannot be null!");
        Preconditions.checkNotNull(item, "'item' cannot be null!");

        int initial = item.getAmount();

        ItemMeta meta = tool.getItemMeta();
        if (meta == null) return initial;

        if (!meta.hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) return initial;

        int level = meta.getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS);

        ThreadLocalRandom random = ThreadLocalRandom.current();
        int i = Math.max((random.nextInt(1, level + 2) - 1), 0);
        return (initial * (i + 1));
    }

}
