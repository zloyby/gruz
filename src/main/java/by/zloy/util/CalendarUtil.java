package by.zloy.util;

import java.util.Calendar;

public class CalendarUtil {
    public static String getDate() {
        return Calendar.getInstance().get(Calendar.DATE)
                + "-" + Calendar.getInstance().get(Calendar.MONTH)
                + "-" + Calendar.getInstance().get(Calendar.YEAR);
    }
}
