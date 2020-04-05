package org.rvrmdz.dynamo.lastfm.services;

import org.apache.commons.lang3.StringUtils;
import org.rvrmdz.dynamo.lastfm.config.LastFmConfig;
import org.yaml.snakeyaml.util.UriEncoder;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LastFmUriBuilder {
    private final LastFmConfig lastFmConfig;

    @Inject
    LastFmUriBuilder(LastFmConfig lastFmConfig) {
        this.lastFmConfig = lastFmConfig;
    }

    String getTopArtistsUri(String user, String period) {
        return String.format(
                lastFmConfig.getApiBaseUrl(),
                lastFmConfig.getTopArtistsMethod(),
                UriEncoder.encode(user),
                StringUtils.isBlank(period) ? "50" : period,
                lastFmConfig.getApiKey());
    }
}