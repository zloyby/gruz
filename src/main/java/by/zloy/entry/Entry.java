package by.zloy.entry;

public class Entry {

    private String title;
    private String href;
    private String summary;
    private String sourceHref;

    public Entry(String title, String href, String summary, String sourceHref) {
        this.title = title;
        this.href = href;
        this.summary = summary;
        this.sourceHref = sourceHref;
    }

    public String getTitle() {
        return title;
    }

    public String getHref() {
        return href;
    }

    public String getSummary() {
        return summary;
    }

    public String getSourceHref() {
        return sourceHref;
    }

    public void setSourceHref(String sourceHref) {
        this.sourceHref = sourceHref;
    }
}
