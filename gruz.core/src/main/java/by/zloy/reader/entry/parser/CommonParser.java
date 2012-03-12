package by.zloy.reader.entry.parser;

import by.zloy.reader.entry.Entry;

public interface CommonParser {

    String createDocumentBody(Entry entry);

    boolean isInPoint(String criteria);
}
