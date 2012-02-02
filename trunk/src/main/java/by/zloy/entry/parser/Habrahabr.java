package by.zloy.entry.parser;

import by.zloy.entry.Entry;

public class Habrahabr implements CommonParser {

    public String createDocumentBody(Entry entry) {
        String href = entry.getHref();
        return href + "TODO: parse html page";
    }
}
