package by.zloy.parser;

import by.zloy.model.Entry;

public class Habrahabr implements Common {

    public String createDocumentBody(Entry entry) {
        String href = entry.getHref();
        return href + "TODO: parse html page";
    }
}
