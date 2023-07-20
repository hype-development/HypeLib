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
import org.jetbrains.annotations.NotNull;

/**
 * Represents a time formatting utility which is used to format time into
 * a human-readable format.
 */
public class TimeUtil {

    /*
     * Format template for the time.
     */
    private static final String format = "%day%%hour%%min%%sec%";

    /**
     * Format a time into a human-readable format.
     * @param time Time to format
     * @param small Whether to use small format
     * @return Formatted time
     */
    @NotNull
    public static String format(long time, boolean small) {
        Preconditions.checkArgument(time >= 0, "Time cannot be smaller than 0.");

        int toSec = (int) (time / 1000) % 60;
        int toMin = (int) ((time / (1000 * 60)) % 60);
        int toHour = (int) ((time / (1000 * 60 * 60)) % 24);
        int toDays = (int) (time / (1000 * 60 * 60 * 24));

        boolean dayNotZero = toDays != 0;
        boolean hourNotZero = toHour != 0;
        boolean minuteNotZero = toMin != 0;
        boolean secondNotZero = toSec != 0;

        String day = (small ? "d" : (toDays == 1 ? "day" : "days"));
        String hour = (small ? "h" : (toHour == 1 ? "hour" : "hours"));
        String minute = (small ? "m" : (toMin == 1 ? "minute" : "minutes"));
        String second = (small ? "s" : (toSec == 1 ? "second" : "seconds"));

        String dayFormat = (dayNotZero ? toDays + (small ? "" : " ") + day + " " : "");
        String hourFormat = (hourNotZero ? toHour + (small ? "" : " ") + hour + " " : "");
        String minuteFormat = (minuteNotZero ? toMin + (small ? "" : " ") + minute + " " : "");
        String secondFormat = (secondNotZero ? toSec + (small ? "" : " ") + second : "");

        return dayFormat + hourFormat + minuteFormat + secondFormat;
    }
}
