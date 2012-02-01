package by.zloy.parser;

import by.zloy.model.Entry;
import by.zloy.util.CalendarUtil;

public class CommonImpl implements Common {

    public static String createDocumentHeader() {
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\"><head>"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>"
                + "<title>" + CalendarUtil.getDate() + "</title>"
                + "</head><body>";
    }

    public static String createDocumentFooter() {
        return "</body></html>";
    }

    public String createDocumentBody(Entry entry) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div><h2>")
                .append(entry.getTitle())
                .append("</h2>")
                .append(entry.getSummary())
                .append("</div><hr/>");
        return sb.toString();
    }
}
