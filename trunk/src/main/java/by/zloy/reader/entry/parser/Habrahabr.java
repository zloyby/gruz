package by.zloy.reader.entry.parser;

import by.zloy.reader.entry.Entry;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.SourceCompactor;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Habrahabr implements CommonParser {

    private static final String TITLE = "title";
    private static final String ENTRY_TITLE = "entry-title";
    private static final String CONTENT = "content";
    private static final String COMMENTS = "comments";

    public String createDocumentBody(Entry entry) {
        String href = entry.getHref();
        StringBuilder sb = new StringBuilder();

        try {
            Source source = new Source(new URL(href));

            //title:
            sb.append("<div><h3>");
            List<Element> posts = source.getAllElementsByClass(TITLE);
            if (posts.isEmpty()) {
                posts = source.getAllElementsByClass(ENTRY_TITLE);
            }
            for (Element post : posts) {
                if (post.getStartTag().getName().equals(HTMLElementName.H2)) {
                    Segment segment = post.getContent();
                    SourceCompactor sc = new SourceCompactor(segment);
                    sb.append(sc.toString());
                }
            }
            sb.append("</h3>");

            //body:
            posts = source.getAllElementsByClass(CONTENT);
            for (Element post : posts) {
                if (post.getStartTag().getName().equals(HTMLElementName.DIV)) {
                    Segment segment = post.getContent();
                    SourceCompactor sc = new SourceCompactor(segment);
                    sb.append(sc.toString());
                }
            }

            //comments:
            Element comments = source.getElementById(COMMENTS);
            if (comments.getStartTag().getName().equals(HTMLElementName.DIV)) {
                Segment segment = comments.getContent();
                SourceCompactor sc = new SourceCompactor(segment);
                sb.append(sc.toString());
            }

            sb.append("</div><hr/>");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public boolean isInPoint(String criteria) {
        return criteria != null && criteria.contains("habrahabr.ru");
    }
}
