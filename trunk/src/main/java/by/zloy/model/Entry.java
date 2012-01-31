package by.zloy.model;

public class Entry {

    private String title;
    private String href;
    private String summary;
    private String source;

    public Entry(String title, String href, String summary, String source) {
        this.title = title;
        this.href = href;
        this.summary = summary;
        this.source = source;
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

    public String getSource() {
        return source;
    }
}
