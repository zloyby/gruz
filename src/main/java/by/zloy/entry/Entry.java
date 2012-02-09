package by.zloy.entry;

public class Entry {

    private String title;
    private String href;
    private String summary;

    public Entry(String title, String href, String summary) {
        this.title = title;
        this.href = href;
        this.summary = summary;
    }

    /**
     * Title of current rss news
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Url of current rss news
     * @return url
     */
    public String getHref() {
        return href;
    }

    /**
     * Information text in rss
     * @return text
     */
    public String getSummary() {
        return summary;
    }
}
