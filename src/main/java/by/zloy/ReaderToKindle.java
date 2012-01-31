package by.zloy;

import be.lechtitseb.google.reader.api.core.GoogleReader;
import be.lechtitseb.google.reader.api.model.exception.AuthenticationException;
import be.lechtitseb.google.reader.api.model.exception.GoogleReaderException;
import by.zloy.model.Entry;
import by.zloy.model.Rss;
import by.zloy.parser.Common;
import by.zloy.parser.CommonImpl;
import by.zloy.util.PropertiesUtil;
import by.zloy.util.SendMailSSL;
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

public class ReaderToKindle {

    public static void main(String[] args)
            throws IOException, AuthenticationException, GoogleReaderException, SAXException,
            ParserConfigurationException {
        new ReaderToKindle().run();
    }

    private void run() throws IOException, AuthenticationException, GoogleReaderException, SAXException,
            ParserConfigurationException {

        // Init
        GoogleReader googleReader = initReader();

        // Get new entries from reader
        String data = getRssData(googleReader);

        // Parse RSS
        List<Entry> entries = parseRss(data);

        if (!entries.isEmpty()) {
            // Create document to send
            String document = createDocument(entries);

            // Send document to @kindle.com for convert
            sendToEmail(document);
        }
    }

    private GoogleReader initReader() throws AuthenticationException {
        GoogleReader googleReader = new GoogleReader(PropertiesUtil.getProperty("auth.mail"),
                                                     PropertiesUtil.getProperty("auth.password"));
        googleReader.login();
        return googleReader;
    }

    private String getRssData(GoogleReader googleReader) throws GoogleReaderException {
        String userId = googleReader.getUserInformation().getUserId();
        String feedId = "user/" + userId + PropertiesUtil.getProperty("kindle.reader.rss.label");
        String data = googleReader.getApi().getUnreadItems(feedId, 1000);
        googleReader.markFeedAsRead(feedId);
        return data;
    }

    private List<Entry> parseRss(String data) throws ParserConfigurationException, SAXException, IOException {
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

    private String createDocument(List<Entry> entries) {
        StringBuilder sb = new StringBuilder();
        sb.append(CommonImpl.createDocumentHeader());
        for (Entry entry : entries) {
            String href = entry.getSource();
            Common common = Rss.getParserClass(href);
            sb.append(common.createDocumentBody(entry));
        }
        sb.append(CommonImpl.createDocumentFooter());
        return sb.toString();
    }

    private void sendToEmail(String document) throws IOException {
        new SendMailSSL().send(document);
    }
}
