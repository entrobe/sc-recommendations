package com.bkmahout.recommender
/**
 * Created by Thoughtworker on 2/24/15.
 */
class MovieLookerUpperTest extends GroovyTestCase {
    void testCanLookupTitles() {
        MovieLookerUpper lookerUpper
        lookerUpper = new MovieLookerUpper("data/movie-information.csv")
        lookerUpper.CreateMovieList()

        assert lookerUpper.GetTitleFromId(1) == "Toy Story (1995)"
    }
}
