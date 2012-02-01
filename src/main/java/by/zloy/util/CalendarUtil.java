package by.zloy.util;

import java.text.DateFormat;
import java.util.Date;

public class CalendarUtil {
    public static String getDate() {
        return DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
    }
}
