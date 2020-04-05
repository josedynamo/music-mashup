package org.rvrmdz.dynamo.lastfm.services;

import com.google.gson.Gson;
import org.rvrmdz.dynamo.lastfm.transport.ErrorMessage;
import org.rvrmdz.dynamo.lastfm.transport.TopArtistsResults;
import org.rvrmdz.dynamo.lastfm.transport.Topartists;
import ratpack.exec.Promise;
import ratpack.http.MediaType;
import ratpack.http.client.HttpClient;
import ratpack.http.client.ReceivedResponse;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.URI;

@Singleton
public class LastFmService {
    private static final String ACCEPT_HEADER = "Accept";
    private final LastFmUriBuilder lastFmUriBuilder;
    private final HttpClient httpClient;
    private static final String USER_NOT_FOUND = "User not Found";
    private Gson gson;


    @Inject
    public LastFmService(Gson gson, LastFmUriBuilder lastFmUriBuilder, HttpClient httpClient) {
        this.gson = gson;
        this.lastFmUriBuilder = lastFmUriBuilder;
        this.httpClient = httpClient;
    }

    public Promise<Object> getTopArtists(String user, String period) {
        URI timeZoneDBApiURI = URI.create(lastFmUriBuilder.getTopArtistsUri(user, period));
        return httpClient.get(timeZoneDBApiURI, requestSpec ->
                requestSpec.getHeaders().set(ACCEPT_HEADER, MediaType.APPLICATION_JSON)).
                mapIf(receivedResponse -> receivedResponse.getStatusCode() == 200,
                        receivedResponse -> extractTopArtists(receivedResponse),
                        receivedResponse -> getErrorResponse());

    }

    public Topartists extractTopArtists(ReceivedResponse receivedResponse) {
        TopArtistsResults topArtistsResults = gson.fromJson(receivedResponse.getBody().getText(), TopArtistsResults.class);
        return topArtistsResults.getTopartists();
    }

    private ErrorMessage getErrorResponse() {
        return new ErrorMessage(USER_NOT_FOUND);
    }
}
