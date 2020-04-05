package org.rvrmdz.dynamo;


import org.rvrmdz.dynamo.lastfm.LastFmModule;
import org.rvrmdz.dynamo.lastfm.config.LastFmConfig;
import org.rvrmdz.dynamo.lastfm.handlers.LastFmTopArtistsHandler;
import ratpack.guice.Guice;
import ratpack.server.RatpackServer;
import ratpack.server.ServerConfig;

public final class MusicMashUpServer {
    private static final String ARTISTS = "artists";

    public static void main(String... args) throws Exception {

        RatpackServer server = RatpackServer.of(spec -> {
            ServerConfig serverConfig = getConfig();
            spec.serverConfig(serverConfig)
                    .registry(Guice.registry(registry -> registry.module(LastFmModule.class)))
                    .handlers(chain -> chain
                            .path(ARTISTS, LastFmTopArtistsHandler.class)
                            .files(f -> f.dir("public").indexFiles("index.html")));
        });
        server.start();
    }


    private static ServerConfig getConfig() {
        return ServerConfig
                .embedded()
                .port(5050)
                .findBaseDir()
                .development(true)
                .json(LastFmConfig.getJsonConfigPath())
                .require("/app/lastFM", LastFmConfig.class)
                .build();
    }
}
