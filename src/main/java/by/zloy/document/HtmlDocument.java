package by.zloy.document;

import by.zloy.entry.Entry;
import by.zloy.util.RssUtil;
import by.zloy.entry.parser.CommonParser;
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

    public HtmlDocument(List<Entry> entries) {
        StringBuilder sb = new StringBuilder();
        sb.append(createDocumentHeader());
        for (Entry entry : entries) {
            CommonParser commonParser = RssUtil.getParserClass(entry.getHref());
            sb.append(commonParser.createDocumentBody(entry));
        }
        sb.append(createDocumentFooter());
        data = sb.toString();
    }

    public File getFile() throws IOException {
        File innerFile = new File(System.getProperty("java.io.tmpdir") + CalendarUtil.getTitleWithDate() + ".htm");
        FileUtils.writeStringToFile(innerFile, data);
        innerFile.deleteOnExit();
        return doZip(innerFile);
    }

    private File doZip(File innerFile) throws IOException {
        File zipFile = new File(System.getProperty("java.io.tmpdir") + CalendarUtil.getTitleWithDate() + ".zip");
        zipFile.deleteOnExit();

        byte[] buf = new byte[1024];
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
        FileInputStream in = new FileInputStream(innerFile);
        out.putNextEntry(new ZipEntry(innerFile.getName()));

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
