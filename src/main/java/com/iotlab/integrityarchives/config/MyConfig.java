package com.iotlab.integrityarchives.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application")
public class MyConfig{

    /**
     * 上传文件路径
     */
    private static String profile;

    public static String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
    	MyConfig.profile = profile;
    }
}