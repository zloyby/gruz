package by.zloy.util;

import by.zloy.entry.Entry;
import by.zloy.entry.parser.CommonParser;
import by.zloy.entry.parser.CommonParserImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({ "UnusedDeclaration" })
public enum RssUtil {
    HABRAHABR("http://www.habrahabr.ru/rss/main/", by.zloy.entry.parser.Habrahabr.class);

    private String url;
    private Class<? extends CommonParser> parserClass;

    RssUtil(String url, Class<? extends CommonParser> parserClass) {
        this.url = url;
        this.parserClass = parserClass;
    }

    public static CommonParser getParserClass(String href) {
        for (RssUtil rss : RssUtil.values()) {
            if (rss.url.equalsIgnoreCase(href)) {
                try {
                    return rss.parserClass.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return new CommonParserImpl();
    }

    public static List<Entry> parseRssString(String data) throws ParserConfigurationException, IOException, SAXException {
        List<Entry> entries = new ArrayList<Entry>();
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(data));

        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("entry");
        for (int i = 0; i < nodes.getLength(); i++) {
            String title = ((Element)nodes.item(i)).getElementsByTagName("title").item(0).getFirstChild().getTextContent();
            String href = ((Element)nodes.item(i)).getElementsByTagName("link").item(0).getAttributes().getNamedItem("href").getFirstChild().getTextContent();
            String summary = ((Element)nodes.item(i)).getElementsByTagName("summary").item(0).getFirstChild().getTextContent();
            String source = ((Element)((Element)nodes.item(i)).getElementsByTagName("source").item(0)).getElementsByTagName("link").item(0).getAttributes().getNamedItem("href").getFirstChild().getTextContent();

            Entry entry = new Entry(title, href, summary, source);
            entries.add(entry);
        }
        return entries;
    }
}
