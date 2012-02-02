package by.zloy.document;

import by.zloy.model.Entry;
import by.zloy.model.Rss;
import by.zloy.parser.CommonParser;
import by.zloy.util.CalendarUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author EugenKozlov
 */
public class HtmlDocument implements Document {

    String data;

    private String createDocumentHeader() {
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\"><head>"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>"
                + "<title>" + CalendarUtil.getTitleWithDate() + "</title>"
                + "</head><body>";
    }

    private String createDocumentFooter() {
        return "</body></html>";
    }

    public HtmlDocument(List<Entry> entries) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(createDocumentHeader());
        for (Entry entry : entries) {
            String href = entry.getSource();
            CommonParser commonParser = Rss.getParserClass(href);
            sb.append(commonParser.createDocumentBody(entry));
        }
        sb.append(createDocumentFooter());
        data = sb.toString();
        getFile();
    }

    public File getFile() throws IOException {
//        File innerFile = File.createTempFile(CalendarUtil.getTitleWithDate(), "html");
//        innerFile.deleteOnExit();

        File innerFile = new File("d:/" + CalendarUtil.getTitleWithDate() + ".html");
        FileUtils.writeStringToFile(innerFile, data);
        return doZip(innerFile);
    }

    private File doZip(File innerFile) throws IOException {
//        File zipFile = File.createTempFile(CalendarUtil.getTitleWithDate(), "zip");
//        innerFile.deleteOnExit();

        File zipFile = new File("d:/" + CalendarUtil.getTitleWithDate() + ".zip");

        byte[] buf = new byte[1024];
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
        FileInputStream in = new FileInputStream(innerFile);
        out.putNextEntry(new ZipEntry(innerFile.getPath()));

        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.closeEntry();
        in.close();
        out.close();

        return zipFile;
    }
}
