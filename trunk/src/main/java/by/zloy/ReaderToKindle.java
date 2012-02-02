package by.zloy;

import be.lechtitseb.google.reader.api.core.GoogleReader;
import be.lechtitseb.google.reader.api.model.exception.AuthenticationException;
import be.lechtitseb.google.reader.api.model.exception.GoogleReaderException;
import by.zloy.document.Document;
import by.zloy.document.HtmlDocument;
import by.zloy.entry.Entry;
import by.zloy.util.RssUtil;
import by.zloy.util.PropertiesUtil;
import by.zloy.util.SendMailSSLUtil;
import org.apache.commons.mail.EmailException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class ReaderToKindle {

    public static void main(String[] args) {
        new ReaderToKindle().run();
    }

    private void run() {
        try {
            // Init
            GoogleReader googleReader = initReader();

            // Get new entries from reader
            String data = getRssData(googleReader);

            // Parse RSS
            List<Entry> entries = parseRss(data);

            if (!entries.isEmpty()) {
                // Create document to send
                Document document = createDocument(entries);

                // Send document to @kindle.com for convert
                sendToEmail(document);
            }
        } catch (EmailException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GoogleReaderException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialization
     *
     * @return reader
     * @throws AuthenticationException ex
     */
    private GoogleReader initReader() throws AuthenticationException {
        GoogleReader googleReader = new GoogleReader(PropertiesUtil.getProperty("auth.mail"),
                                                     PropertiesUtil.getProperty("auth.password"));
        googleReader.login();
        return googleReader;
    }

    /**
     * Get string of unreaded rss
     *
     * @param googleReader reader
     * @return string
     * @throws GoogleReaderException ex
     */
    private String getRssData(GoogleReader googleReader) throws GoogleReaderException {
        String userId = googleReader.getUserInformation().getUserId();
        String feedId = "user/" + userId + PropertiesUtil.getProperty("kindle.reader.rss.label");
        String data = googleReader.getApi().getUnreadItems(feedId, 1000);
        //googleReader.markFeedAsRead(feedId);
        return data;
    }

    /**
     * Parsing string of data to list of entries
     *
     * @param data string
     * @return list of entries
     * @throws ParserConfigurationException ex
     * @throws SAXException                 ex
     * @throws IOException                  ex
     */
    private List<Entry> parseRss(String data) throws ParserConfigurationException, SAXException, IOException {
        return RssUtil.parseRssString(data);
    }

    /**
     * Method for choose document to create and send (html, doc, etc.)
     * Now implemented only HTML documents.
     *
     * @param entries list of rss
     * @return document
     */
    private Document createDocument(List<Entry> entries) {
        return new HtmlDocument(entries);
    }

    /**
     * Sent to email
     *
     * @param document doc
     * @throws EmailException  ex
     * @throws java.io.IOException ex
     */
    private void sendToEmail(Document document) throws IOException, EmailException {
        SendMailSSLUtil.send(document, "");
    }
}
