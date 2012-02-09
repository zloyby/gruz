package by.zloy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarUtil {
    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH-mm");

    public static String getDate() {
        return format.format(new Date());
    }

    public static String getTitleWithDate() {
        return "Google Reader [" + getDate() + "]";
    }
}
