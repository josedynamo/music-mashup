package org.rvrmdz.dynamo.lastfm;

import com.google.inject.AbstractModule;
import org.rvrmdz.dynamo.lastfm.handlers.LastFmTopArtistsHandler;

public class LastFmModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LastFmTopArtistsHandler.class);


    }
}
