package by.epamtc.utilities.dao.impl.pool;

import java.util.ResourceBundle;

public class TestDBResourceManager {
    private final static TestDBResourceManager instance = new TestDBResourceManager();
    private final static String DB_PROPERTIES_FILE = "db";

    private final ResourceBundle bundle = ResourceBundle.getBundle(DB_PROPERTIES_FILE);

    public static TestDBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
