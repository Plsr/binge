package io.megaquiche.binge.data;

import io.megaquiche.binge.models.SearchResults;
import io.megaquiche.binge.models.Season;
import io.megaquiche.binge.models.Series;
import io.megaquiche.binge.utils.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by @timomeh on 05/02/2017.
 */

public class SeriesService {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    // TODO: API_KEY can be reverse engineered. Should be hidden somehow.
    private static final String API_KEY = "1dced2fb1cbb9c4bbee8fbb7a6d83f67";
    private TheMovieDatabase mTheMovieDatabase;

    /**
     * Interface of SeriesService for Retrofit
     */
    public interface TheMovieDatabase {
        @GET("search/tv")
        Call<SearchResults> searchSeries(
                @Query("query") String search,
                @Query("page") int pageNumber);

        @GET("tv/{seriesId}")
        Call<Series> getSeries(
                @Path("seriesId") int seriesId);

        @GET("tv/{seriesId}/season/{seasonNumber}")
        Call<Season> getSeason(
                @Path("seriesId") int seriesId,
                @Path("seasonNumber") int seasonNumber);
    }

    /**
     * Async Callback after the Execution of a Request.
     * Used as a wrapper around Retrofit's Callback.
     */
    public interface Next<T> {
        void onSuccess(T result, Response response);
        void onError();
    }

    public SeriesService() {
        mTheMovieDatabase = new RetrofitFactory()
                .addBaseUrl(BASE_URL)
                .addGsonSupport()
                .addReuseConnectionSupport()
                .addQueryParameterToUrl("api_key", API_KEY)
                .buildForService(SeriesService.TheMovieDatabase.class);
    }

    public SeriesService(String baseUrl) {
        mTheMovieDatabase = new RetrofitFactory()
                .addBaseUrl(baseUrl)
                .addGsonSupport()
                .addReuseConnectionSupport()
                .addQueryParameterToUrl("api_key", API_KEY)
                .buildForService(SeriesService.TheMovieDatabase.class);
    }

    /**
     * Get the plain Retrofit Service instead of this Wrapper around it.
     * Good for synchronous use.
     *
     * @return The Retrofit Service
     */
    public SeriesService.TheMovieDatabase getService() {
        return mTheMovieDatabase;
    }

    /**
     * Execute a Retrofit Call.
     *
     * @param call The Retrofit Call
     * @param next Callback after Request Execution
     */
    private <T> void call(Call<T> call, final Next<T> next) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    next.onSuccess(response.body(), response);
                } else {
                    next.onError();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                next.onError();
            }
        });
    }

    /**
     * Search a Series.
     *
     * @param query Search Query
     * @param next  Callback
     */
    public void search(String query, Next<SearchResults> next) {
        search(query, 1, next);
    }

    /**
     * Search a specific Series result page.
     * @param query       Search Query
     * @param pageNumber  Page Number
     * @param next        Callback
     */
    public void search(String query, int pageNumber, Next<SearchResults> next) {
        call(mTheMovieDatabase.searchSeries(query, pageNumber), next);
    }

    /**
     * Get a Series by itself. Seems redundant, maybe that's true, but maybe that's usable for
     * refreshing data.
     *
     * @param series Plain Series Object
     * @param next   Callback
     */
    public void getSeries(Series series, Next<Series> next) {
        call(mTheMovieDatabase.getSeries(series.getId()), next);
    }

    /**
     * Get a Series by TheMovieDatabase ID.
     *
     * @param seriesId TheMovieDatabase ID of the Series
     * @param next     Callback
     */
    public void getSeries(int seriesId, Next<Series> next) {
        call(mTheMovieDatabase.getSeries(seriesId), next);
    }

    /**
     * Get a Season by itself. Seems redundant, maybe that's true, but maybe that's usable for
     * refreshing data.
     *
     * @param series Plain Series Object
     * @param season Plain Season Object
     * @param next   Callback
     */
    public void getSeason(Series series, Season season, Next<Season> next) {
        call(mTheMovieDatabase.getSeason(series.getId(), season.getNumber()), next);
    }

    /**
     * Get a Season.
     *
     * @param series        Plain Series Object
     * @param seasonNumber  Number of Season
     * @param next          Callback
     */
    public void getSeason(Series series, int seasonNumber, Next<Season> next) {
        call(mTheMovieDatabase.getSeason(series.getId(), seasonNumber), next);
    }

    /**
     * Get a Season, but this time by a seriesID and the Season Object itself.
     * I think this is very meaningless, but hey, it's something.
     *
     * @param seriesId TheMovieDatabase ID of Series
     * @param season   Plain Season Object
     * @param next     Callback
     */
    public void getSeason(int seriesId, Season season, Next<Season> next) {
        call(mTheMovieDatabase.getSeason(seriesId, season.getNumber()), next);
    }

    /**
     * Get a Season.
     *
     * @param seriesId      TheMovieDatabase ID of Series
     * @param seasonNumber  Number of Season
     * @param next          Callback
     */
    public void getSeason(int seriesId, int seasonNumber, Next<Season> next) {
        call(mTheMovieDatabase.getSeason(seriesId, seasonNumber), next);
    }

}
