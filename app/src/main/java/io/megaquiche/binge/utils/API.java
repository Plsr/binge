package io.megaquiche.binge.utils;

import java.io.IOException;

import io.megaquiche.binge.pojo.SearchResults;
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
import retrofit2.http.Query;

/**
 * Created by Timo Maemecke (@timomeh) on 01/06/16.
 * <p/>
 * TODO: Add a class header comment
 */
public class API {
    public static final String TMDB_API_URL = "http://api.themoviedb.org/3/";
    private static final String TMDB_API_KEY = "1dced2fb1cbb9c4bbee8fbb7a6d83f67";

    public interface TheMovieDB {
        @GET("search/tv")
        Call<SearchResults> searchSeries(@Query("query") String query, @Query("page") int page);
    }

    public interface Res<T> {
        void onSuccess(T result);
        void onError();
    }

    private static OkHttpClient.Builder mHttpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder mBuilder = new Retrofit.Builder()
            .baseUrl(TMDB_API_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static TheMovieDB mTmdb = API.createService(API.TheMovieDB.class);

    public static <S> S createService(Class<S> serviceClass) {
        System.out.println(TMDB_API_URL);
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
        public static void searchSeries(String query, final Res<SearchResults> res) {
            searchSeries(query, 1, res);
        }

        public static void searchSeries(String query, int page, final Res<SearchResults> res) {
            Call<SearchResults> call = mTmdb.searchSeries(query, page);
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
    }

    public static void main(String[] args) {
        API.Req.searchSeries("doctor", new Res<SearchResults>() {
            @Override
            public void onSuccess(SearchResults result) {
                for (Series series : result.getSeriesList()) {
                    System.out.println(series.getName());
                }
            }

            @Override
            public void onError() {
                System.out.println("Error!");
            }
        });
    }


}
