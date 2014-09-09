package org.study.security.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by haoyuewen on 9/8/14.
 */
@Component
public class PropertiesUtil {

    @Value("commonConfig")
    private Properties commonConfig;

    public String get(String key) {
        return (String) commonConfig.get(key);
    }

    public void set(String key, String value) {
        commonConfig.put(key, value);
    }
}
