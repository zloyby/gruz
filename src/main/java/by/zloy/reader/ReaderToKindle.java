package by.zloy.reader;

import be.lechtitseb.google.reader.api.core.GoogleReader;
import be.lechtitseb.google.reader.api.model.exception.AuthenticationException;
import be.lechtitseb.google.reader.api.model.exception.GoogleReaderException;
import by.zloy.reader.document.Document;
import by.zloy.reader.document.HtmlDocument;
import by.zloy.reader.entry.Entry;
import by.zloy.reader.util.RssUtil;
import by.zloy.reader.util.PropertiesUtil;
import by.zloy.reader.util.SendMailSSLUtil;
import com.sun.syndication.io.FeedException;
import org.apache.commons.mail.EmailException;

import java.io.IOException;
import java.util.List;

public class ReaderToKindle {

    public static void main(String[] args) {
        new ReaderToKindle().run();
    }

    private void run() {
        try {
            System.out.println("Start ReaderToKindle...");
            GoogleReader googleReader = initReader();
            System.out.println("Auth successful...");
            String data = getRssData(googleReader);
            System.out.println("Get data from reader of " + data.length() + " symbols...");
            List<Entry> entries = parseRss(data);
            System.out.println("Convert data to  " + entries.size() + " entries...");
            if (!entries.isEmpty()) {
                Document document = createDocument(entries);
                System.out.println("Create document...");
                sendToEmail(document);
                System.out.println("Send to email and finish...");
            }
        } catch (EmailException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GoogleReaderException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (FeedException e) {
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
        String feedId = "user/" + userId + "/label/" + PropertiesUtil.getProperty("kindle.reader.rss.label");
        String data = googleReader.getApi().getUnreadItems(feedId, Integer.parseInt(PropertiesUtil.getProperty("kindle.reader.count.new.rss")));
        if (Boolean.parseBoolean(PropertiesUtil.getProperty("kindle.reader.make.unread"))) {
            googleReader.markFeedAsRead(feedId);
        }
        return data;
    }

    /**
     * Parsing string of data to list of entries
     *
     * @param data string
     * @return list of entries
     * @throws IOException                          ex
     * @throws com.sun.syndication.io.FeedException ex
     * @throws IllegalArgumentException             ex
     */
    private List<Entry> parseRss(String data) throws IllegalArgumentException, FeedException, IOException {
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
     * @throws EmailException      ex
     * @throws java.io.IOException ex
     */
    private void sendToEmail(Document document) throws IOException, EmailException {
        SendMailSSLUtil.send(document, "");
    }
}
