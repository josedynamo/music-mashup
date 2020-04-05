# Music Mashup

This app mashes several free music apis to aggregate useful data about the artists.

## Run Tests

To run the tests, open a terminal and type the command :  _gradle test_

## Run
To run the app just open the terminal and type the command : _gradle run_ 

Click on the link where the app is running that is shown in the console.  


## High Level Specs 

1. Get list of top Artists from a specific (Last.FM)[www.last.fm] user via the Last.FM [API](https://www.last.fm/api/show/user.getTopArtists). ✔
2. Get the list of next shows for those artists from Songkick (The [Music Brainz](https://musicbrainz.org/) ID (**mbid**) gotten from last.fm can be used for that.)
   For more details go [here](https://www.songkick.com/developer/upcoming-events-for-artist). 
3. Create a Spotify playlist with random songs from those artists.
4. Create a simple UI that shows:
 - The top artists of a LastFM user.
 - The upcoming events for each artist when it's name is clicked.
5. Persist the data to avoid doing repeating the same calls.

And more... 

## 12 Factor Application Compliance Check 

1. Codebase: One codebase tracked in revision control ✔ , many deploys (TODO. CI setup missing).
2. Dependencies: Explicitly declare and isolate dependencies
3. Config: Store config in the environment ✔
4. Backing services: Treat backing services as attached resources (Partly done. Better Abstraction needed.)
5. Build, release, run: Strictly separate build and run stages (Partly done. CI setup missing.)
6. Processes: Execute the app as one or more stateless processes ✔
7. Port binding: Export services via port binding ?
8. Concurrency: Scale out via the process model (TODO)
9. Disposability: Maximize robustness with fast startup and graceful shutdown (I think it's done but will have to read more about i)
10. Dev/prod parity: Keep development, staging, and production as similar as possible (TODO)
11. Logs: Treat logs as event streams (TODO)
12. Admin processes. Run admin/management tasks as one-off processes (??? Have to read more about it.)

 For more information go [here](https://12factor.net).
 
## Disclaimer

I know... I'm using their Prod environments for the tests. 

## Stack
- [Ratpack](https://ratpack.io/manual/current/intro.html#goals)
- [Spock](http://spockframework.org/spock/docs/1.3/spock_primer.html)
- [Gradle](https://docs.gradle.org/current/userguide/tutorial_using_tasks.html#sec:projects_and_tasks)
- [Handlebars](https://handlebarsjs.com/guide/)
