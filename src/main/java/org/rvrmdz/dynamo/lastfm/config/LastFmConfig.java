package org.rvrmdz.dynamo.lastfm.config;

import com.google.common.io.Resources;

import javax.inject.Singleton;
import java.net.URL;

@Singleton
public class LastFmConfig {

    private static final String CONFIGURATION_FILE_NAME = "config.json";
    private static final URL jsonConfig = Resources.getResource(CONFIGURATION_FILE_NAME);

    private String apiKey;

    private String apiBaseUrl;

    private String topArtistsMethod;

    public String getTopArtistsMethod() {
        return topArtistsMethod;
    }

    public static URL getJsonConfigPath() {
        return jsonConfig;
    }

    public String getApiBaseUrl() {
        return apiBaseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

}
