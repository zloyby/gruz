package by.zloy.reader.util;

import java.util.ResourceBundle;

public class PropertiesUtil {

    private static final PropertiesUtil INSTANCE = new PropertiesUtil();

    private static final String AUTH = "auth";

    private ResourceBundle auth;

    public static PropertiesUtil getInstance() {
        return INSTANCE;
    }

    private PropertiesUtil() {
        auth = ResourceBundle.getBundle(AUTH);
    }

    private ResourceBundle getBundleAuth() {
        return auth;
    }

    public static String getProperty(String key) {
        return PropertiesUtil.getInstance().getBundleAuth().getString(key);
    }
}
