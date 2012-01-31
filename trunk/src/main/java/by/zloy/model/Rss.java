package by.zloy.model;

import by.zloy.parser.Common;
import by.zloy.parser.CommonImpl;

public enum Rss {
    HABRAHABR("http://www.habrahabr.ru/rss/main/", by.zloy.parser.Habrahabr.class);

    private String url;
    private Class<? extends Common> parserClass;

    Rss(String url, Class<? extends Common> parserClass) {
        this.url = url;
        this.parserClass = parserClass;
    }

    public static Common getParserClass(String href) {
        for (Rss rss : Rss.values()) {
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
        return new CommonImpl();
    }
}
