package hu.alkfejl.config;

import java.util.Properties;

public class CsapatConfiguration {
    private static Properties props = new Properties();

    static {
        try {
            props.load(CsapatConfiguration.class.getResourceAsStream("/application.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Properties getProps() {
        return props;
    }

    public static String getValue(String key) {
        return props.getProperty(key);
    }
}
