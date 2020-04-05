package org.rvrmdz.dynamo.lastfm.handlers;

import org.apache.commons.lang3.StringUtils;
import org.rvrmdz.dynamo.lastfm.services.LastFmService;
import org.rvrmdz.dynamo.lastfm.transport.ErrorMessage;
import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.Status;
import ratpack.jackson.Jackson;

import javax.inject.Inject;
import javax.inject.Singleton;

import static ratpack.jackson.Jackson.json;

@Singleton
public class LastFmTopArtistsHandler implements Handler {
    private static final String LASTFM_USER = "user";
    public static final String PLEASE_ENTER_A_LAST_FM_SER_NAME = "Please enter a Last.fm user name";
    private static final String PERIOD = "period";

    private LastFmService lastFMService;

    @Inject
    public LastFmTopArtistsHandler(LastFmService lastFMService) {
        this.lastFMService = lastFMService;
    }

    @Override
    public void handle(Context ctx) {
        String period = ctx.getRequest().getQueryParams().get(PERIOD);
        Promise.value(ctx.getRequest().getQueryParams().get(LASTFM_USER))
                .route(
                        StringUtils::isBlank,
                        user -> {
                            ctx.getResponse().status(Status.BAD_REQUEST);
                            ctx.render(json(new ErrorMessage(PLEASE_ENTER_A_LAST_FM_SER_NAME)));
                        })
                .flatMap(user -> lastFMService.getTopArtists(user, period))
                .map(Jackson::json)
                .then(ctx::render);
    }

}
