import groovy.json.JsonSlurper
import org.rvrmdz.dynamo.MusicMashUpServer
import org.rvrmdz.dynamo.lastfm.handlers.LastFmTopArtistsHandler
import org.rvrmdz.dynamo.lastfm.transport.ErrorMessage
import org.rvrmdz.dynamo.lastfm.transport.Topartists
import ratpack.http.client.ReceivedResponse
import ratpack.test.MainClassApplicationUnderTest
import spock.lang.Shared
import spock.lang.Specification

class LastFmSpec extends Specification {

    def mainClassApplicationUnderTest = new MainClassApplicationUnderTest(MusicMashUpServer.class)

    @Shared
    def json = new JsonSlurper()

    def "Check LastFm TopArtists Handler has status 400 and errorMessage 'Please enter a Last.fm user name' when request is missing user param"() {
        when:
        def response = getFromLastFM "artists?user="

        then:
        response.statusCode == 400

        and:
        def errorMessage = json.parseText(response.body.text) as ErrorMessage
        errorMessage.errorMessage == LastFmTopArtistsHandler.PLEASE_ENTER_A_LAST_FM_SER_NAME

    }

    def "Kasabian and the Foo Fighters are two of josebcn's top artists"() {
        when:
        def response = getFromLastFM "artists?user=josebcn"


        then:
        response.statusCode == 200

        and:
        def topArtists = json.parseText(response.body.text) as Topartists
        topArtists.artist.findAll { it.name == "Kasabian" || it.name == "Foo Fighters" }.size() == 2
    }

    def "Hinds and The Doors are two of steprobe's top artists "() {
        when:
        def response = getFromLastFM "artists?user=steprobe"


        then:
        response.statusCode == 200

        and:
        def topArtists = json.parseText(response.body.text) as Topartists
        topArtists.artist.findAll { it.name == "Hinds" || it.name == "John Frusciante" }.size() == 2
    }


    def "Tame Impala and Explosions in the Sky  are two of cliffmountain'a the top artists"() {
        when:
        def response = getFromLastFM "artists?user=cliffmountain"


        then:
        response.statusCode == 200

        and:
        def topArtists = json.parseText(response.body.text) as Topartists
        assert topArtists.artist.findAll { it.name == "Tame Impala" || it.name == "Explosions in the Sky" }.size() == 2
    }


    private ReceivedResponse getFromLastFM(String query) {
        mainClassApplicationUnderTest.httpClient.get(query)
    }


}
