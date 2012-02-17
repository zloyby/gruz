package by.zloy.reader.entry.parser;

import by.zloy.reader.entry.Entry;

/**
 * User: ArthurPoleshchuk
 * Date: 2/9/12
 * Time: 11:57 AM
 */
public class LiveJournal implements CommonParser {
    public String createDocumentBody(Entry entry) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div><h3>")
                .append(entry.getTitle())
                .append("</h3>")
                .append(entry.getSummary())
                .append("</div><hr/>");
        return sb.toString();
    }

    public boolean isInPoint(String criteria) {
        return criteria != null && criteria.contains("livejournal.com");
    }
}
