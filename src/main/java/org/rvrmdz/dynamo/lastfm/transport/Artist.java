package org.rvrmdz.dynamo.lastfm.transport;

import java.util.Objects;

public class Artist {
    private String mbid;
    private String name;
    private String url;
    private String playcount;

    public String getMbid() {
        return mbid;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getPlaycount() {
        return playcount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        Artist artist = (Artist) o;
        return Objects.equals(getMbid(), artist.getMbid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMbid());
    }
}
