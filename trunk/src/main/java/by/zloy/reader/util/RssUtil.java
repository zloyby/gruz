package by.zloy.reader.util;

import by.zloy.reader.entry.Entry;
import by.zloy.reader.entry.parser.CommonParser;
import by.zloy.reader.entry.parser.CommonParserImpl;
import by.zloy.reader.entry.parser.Habrahabr;
import by.zloy.reader.entry.parser.LiveJournal;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public enum RssUtil {

    HABRAHABR(new Habrahabr()),
    LIVEJOURNAL(new LiveJournal());

    private CommonParser parser;

    RssUtil(CommonParser parser) {
        this.parser = parser;
    }

    public static CommonParser getParserClass(String href) {
        for (RssUtil rss : RssUtil.values()) {
            if (rss.parser.isInPoint(href)) {
                return rss.parser;
            }
        }
        return new CommonParserImpl();
    }

    private static SyndFeed parseFeed(String xml) throws IllegalArgumentException, FeedException, IOException {
        return new SyndFeedInput().build(new XmlReader(new ByteArrayInputStream(xml.getBytes())));
    }

    public static List<Entry> parseRssString(String data) throws IllegalArgumentException, FeedException, IOException {
        List<Entry> entries = new ArrayList<Entry>();
        SyndFeed feed = parseFeed(data);
        for (Object object : feed.getEntries()) {
            SyndEntry entry = (SyndEntry) object;
            String title = entry.getTitle();
            String href = entry.getLink();
            String summary = entry.getDescription().getValue();
            entries.add(new Entry(title, href, summary));
        }
        return entries;
    }
}
