package io.megaquiche.binge.utils;

import java.io.IOException;

import io.megaquiche.binge.pojo.SearchResults;
import io.megaquiche.binge.pojo.Season;
import io.megaquiche.binge.pojo.Series;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Timo Maemecke (@timomeh) on 01/06/16.
 * <p/>
 * TODO: Add a class header comment
 */
public class API {
    public static final String TMDB_API_URL = "https://api.themoviedb.org/3/";
    private static final String TMDB_API_KEY = "1dced2fb1cbb9c4bbee8fbb7a6d83f67";

    /**
     * Interface declares all API methods
     */
    public interface TheMovieDB {
        @GET("search/tv")
        Call<SearchResults> searchSeries(@Query("query") String query, @Query("page") int page,
                                         @Query("language") String language);

        @GET("tv/{seriesId}")
        Call<Series> getSeries(@Path("seriesId") int seriesId, @Query("language") String language);

        @GET("tv/{seriesId}/season/{seasonNumber}")
        Call<Season> getSeason(@Path("seriesId") int seriesId,
                               @Path("seasonNumber") int seasonNumber,
                               @Query("language") String language);
    }

    /**
     * Response Interface
     * @param <T> Class of Response
     */
    public interface Res<T> {
        void onSuccess(T result);
        void onError();
    }

    private static OkHttpClient.Builder mHttpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder mBuilder = new Retrofit.Builder()
            .baseUrl(TMDB_API_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static TheMovieDB mTmdb = API.createService(API.TheMovieDB.class);

    /**
     * Create Request Service Client
     * w/ api_key attachment for each request
     */
    public static <S> S createService(Class<S> serviceClass) {
        mHttpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                HttpUrl url = chain.request().url()
                        .newBuilder()
                        .addQueryParameter("api_key", TMDB_API_KEY)
                        .build();

                Request request = chain.request().newBuilder().url(url).build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = mBuilder.client(mHttpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    public static class Req {
        /**
         * search/tv by query (for first result page)
         * @param query Search query
         * @param res Response Listener
         */
        public static void searchSeries(String query, String language,
                                        final Res<SearchResults> res) {
            searchSeries(query, 1, language, res);
        }

        /**
         * search/tv by query
         * @param query Search query
         * @param page Result page
         * @param res Response Listener
         */
        public static void searchSeries(String query, int page, String language,
                                        final Res<SearchResults> res) {
            Call<SearchResults> call = mTmdb.searchSeries(query, page, language);
            call.enqueue(new Callback<SearchResults>() {
                @Override
                public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                    if (response.isSuccessful()) {
                        res.onSuccess(response.body());
                    } else {
                        res.onError();
                    }
                }

                @Override
                public void onFailure(Call<SearchResults> call, Throwable t) {
                    res.onError();
                }
            });
        }

        /**
         * Get Series Information
         * @param seriesId TheMovieDB ID of series
         * @param res Response Listener
         */
        public static void getSeries(int seriesId, String language, final Res<Series> res) {
            Call<Series> call = mTmdb.getSeries(seriesId, language);
            call.enqueue(new Callback<Series>() {
                @Override
                public void onResponse(Call<Series> call, Response<Series> response) {
                    if (response.isSuccessful()) {
                        res.onSuccess(response.body());
                    } else {
                        res.onError();
                    }
                }

                @Override
                public void onFailure(Call<Series> call, Throwable t) {
                    res.onError();
                }
            });
        }

        /**
         * Get Season Information
         * @param seriesId TheMovieDB ID of series
         * @param seasonNumber Number of Season (NOT ID!)
         * @param res Response Listener
         */
        public static void getSeason(int seriesId, int seasonNumber, String language,
                                     final Res<Season> res) {
            Call<Season> call = mTmdb.getSeason(seriesId, seasonNumber, language);
            call.enqueue(new Callback<Season>() {
                @Override
                public void onResponse(Call<Season> call, Response<Season> response) {
                    if (response.isSuccessful()) {
                        res.onSuccess(response.body());
                    } else {
                        res.onError();
                    }
                }

                @Override
                public void onFailure(Call<Season> call, Throwable t) {
                    res.onError();
                }
            });
        }
    }

    // Just a little sandbox here
    // Grab it for examples while I'm too lazy to write tests
    public static void main(String[] args) {
//        API.Req.searchSeries("doctor", "de", new Res<SearchResults>() {
//            @Override
//            public void onSuccess(SearchResults result) {
//                for (Series series : result.getSeriesList()) {
//                    System.out.println(series.getName());
//                    System.out.println(series.getDescription());
//                    System.out.println();
//                }
//            }
//
//            @Override
//            public void onError() {
//                System.out.println("Error!");
//            }
//        });

        API.Req.getSeries(48552, "de", new Res<Series>() {
            @Override
            public void onSuccess(Series result) {
                System.out.println(result.getName());
                System.out.println(result.getDescription());
                System.out.println(result.getImageUrl());

                for (Season season : result.getSeasons()) {
                    System.out.println("Season " + season.getNumber() + ": " + season.getId());
                }
            }

            @Override
            public void onError() {
                System.out.println("Error!");
            }
        });

//        API.Req.getSeason(57243, 0, new Res<Season>() {
//            @Override
//            public void onSuccess(Season result) {
//                System.out.println("Season Nr: " + result.getNumber());
//                for (Episode episode : result.getEpisodes()) {
//                    System.out.println("--- Episode: " + episode.getNumber() +
//                            "(" + episode.getEpisodeCode() + ")");
//                    System.out.println("    " + episode.getName());
//                    System.out.println();
//                }
//            }
//
//            @Override
//            public void onError() {
//                System.out.println("Error!");
//            }
//        });
    }


}
