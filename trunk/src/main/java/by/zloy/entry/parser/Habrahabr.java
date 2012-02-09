package by.zloy.entry.parser;

import by.zloy.entry.Entry;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.SourceCompactor;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Habrahabr implements CommonParser {

    public String createDocumentBody(Entry entry) {
        StringBuilder result = new StringBuilder();
        String href = entry.getHref();
        try {
            Source source = new Source(new URL(href));

            List<Element> posts = source.getAllElementsByClass("post");
            for (Element post : posts) {
                Segment segment = post.getContent();
                SourceCompactor sc = new SourceCompactor(segment);
                result.append(sc.toString());
            }

            List<Element> answers = source.getAllElementsByClass("comments_list");
            for (Element answer : answers) {
                Segment segment = answer.getContent();
                SourceCompactor sc = new SourceCompactor(segment);
                result.append(sc.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public boolean isInPoint(String criteria) {
        return criteria != null && criteria.contains("habrahabr.ru");
    }
}
