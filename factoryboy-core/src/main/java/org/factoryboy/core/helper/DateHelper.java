package org.factoryboy.core.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date & DateTime Helper.
 *
 * @author crazycode@gmail.com
 *
 */
public final class DateHelper {

    /**
     * get Date from String.
     *
     * @param dateSource
     *            format as 'yyyy-MM-dd HH:mm' or 'yyyy-MM-dd'
     * @return
     */
    public static Date t(final String dateSource) {
        String[] dateFormats = new String[] { "yyyy-MM-dd HH:mm",
                "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" };

        for (String dateFormat : dateFormats) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            try {
                return sdf.parse(dateSource);
            } catch (ParseException e) {
                // do nothing.
            }
        }
        throw new RuntimeException(
                "the dateSource("
                        + dateSource
                        + ") MUST format as 'yyyy-MM-dd HH:mm:ss' or 'yyyy-MM-dd HH:mm' or 'yyyy-MM-dd'.");

    }


    public static Date minutsAfter(int minuts) {
        return minutsAfter(new Date(), minuts);
    }

    public static Date minutsAfter(String dateSource, int minuts) {
        return minutsAfter(t(dateSource), minuts);
    }

    public static Date minutsAfter(Date date, int minuts) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minuts);
        return cal.getTime();
    }

    public static Date minutsBefore(int minuts) {
        return minutsBefore(new Date(), minuts);
    }

    public static Date minutsBefore(String dateSource, int minuts) {
        return minutsBefore(t(dateSource), minuts);
    }

    public static Date minutsBefore(Date date, int minuts) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, -minuts);
        return cal.getTime();
    }

    public static Date hoursAfter(int hours) {
        return hoursAfter(new Date(), hours);
    }

    public static Date hoursAfter(String dateSource, int hours) {
        return hoursAfter(t(dateSource), hours);
    }

    public static Date hoursAfter(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hours);
        return cal.getTime();
    }

    public static Date hoursBefore(int hours) {
        return hoursBefore(new Date(), hours);
    }

    public static Date hoursBefore(String dateSource, int hours) {
        return hoursBefore(t(dateSource), hours);
    }

    public static Date hoursBefore(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, -hours);
        return cal.getTime();
    }

    public static Date daysAfter(int days) {
        return daysAfter(new Date(), days);
    }

    public static Date daysAfter(String dateSource, int days) {
        return daysAfter(t(dateSource), days);
    }

    public static Date daysAfter(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    public static Date daysBefore(int days) {
        return daysBefore(new Date(), days);
    }

    public static Date daysBefore(String dateSource, int days) {
        return daysBefore(t(dateSource), days);
    }

    public static Date daysBefore(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, -days);
        return cal.getTime();
    }
}
