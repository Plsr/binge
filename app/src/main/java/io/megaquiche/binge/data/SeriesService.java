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
    private static final String API_KEY = "1dced2fb1cbb9c4bbee8fbb7a6d83f67";
    private TheMovieDatabase mTheMovieDatabase;

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

    public interface Next<T> {
        void onSuccess(T result, Response response);
        void onError();
    }

    public SeriesService() {
        mTheMovieDatabase = new RetrofitFactory()
                .addBaseUrl(BASE_URL)
                .addGsonSupport()
                .addApiKey("api_key", API_KEY)
                .buildForService(SeriesService.TheMovieDatabase.class);
    }

    public SeriesService.TheMovieDatabase getService() {
        return mTheMovieDatabase;
    }

    public SeriesService(String baseUrl) {
        mTheMovieDatabase = new RetrofitFactory()
                .addBaseUrl(baseUrl)
                .addGsonSupport()
                .addApiKey("api_key", API_KEY)
                .buildForService(SeriesService.TheMovieDatabase.class);
    }

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

    public void search(String query, Next<SearchResults> next) {
        search(query, 1, next);
    }

    public void search(String query, int pageNumber, Next<SearchResults> next) {
        call(mTheMovieDatabase.searchSeries(query, pageNumber), next);
    }

    public void getSeries(Series series, Next<Series> next) {
        call(mTheMovieDatabase.getSeries(series.getId()), next);
    }

    public void getSeries(int seriesId, Next<Series> next) {
        call(mTheMovieDatabase.getSeries(seriesId), next);
    }

    public void getSeason(Series series, Season season, Next<Season> next) {
        call(mTheMovieDatabase.getSeason(series.getId(), season.getNumber()), next);
    }

    public void getSeason(Series series, int seasonNumber, Next<Season> next) {
        call(mTheMovieDatabase.getSeason(series.getId(), seasonNumber), next);
    }

    public void getSeason(int seriesId, Season season, Next<Season> next) {
        call(mTheMovieDatabase.getSeason(seriesId, season.getNumber()), next);
    }

    public void getSeason(int seriesId, int seasonNumber, Next<Season> next) {
        call(mTheMovieDatabase.getSeason(seriesId, seasonNumber), next);
    }

}
