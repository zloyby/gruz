package by.zloy.parser;

import by.zloy.model.Entry;
import by.zloy.util.CalendarUtil;

public class CommonImpl implements Common {

    public static String createDocumentHeader() {
        return "<html><head><title>" + CalendarUtil.getDate() +"</title></head><body>";
    }

    public static String createDocumentFooter() {
        return "</body></html>";
    }

    public String createDocumentBody(Entry entry) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>")
                .append(entry.getTitle())
                .append("</h2>")
                .append("<br/>")
                .append(entry.getSummary())
                .append("<br/>")
                .append("<hr/>");
        return sb.toString();
    }
}
