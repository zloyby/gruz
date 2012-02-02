package by.zloy.parser;

import by.zloy.model.Entry;

public class CommonParserImpl implements CommonParser {

    public String createDocumentBody(Entry entry) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div><h3>")
                .append(entry.getTitle())
                .append("</h3>")
                .append(entry.getSummary())
                .append("</div><hr/>");
        return sb.toString();
    }
}