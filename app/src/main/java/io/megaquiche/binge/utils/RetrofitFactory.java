package io.megaquiche.binge.utils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Factory to create a customized Retrofit Client.
 * Created by @timomeh on 05/02/2017.
 */

public class RetrofitFactory {
    private static OkHttpClient.Builder mHttpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder mBuilder = new Retrofit.Builder();

    public RetrofitFactory() { }

    public RetrofitFactory addBaseUrl(final String baseUrl) {
        mBuilder.baseUrl(baseUrl);
        return this;
    }

    public RetrofitFactory addGsonSupport() {
        mBuilder.addConverterFactory(GsonConverterFactory.create());
        return this;
    }

    public RetrofitFactory addApiKey(final String queryParameter, final String apiKey) {
        mHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                HttpUrl url = chain.request().url()
                        .newBuilder()
                        .addQueryParameter(queryParameter, apiKey)
                        .build();

                Request request = chain.request().newBuilder().url(url).build();
                return chain.proceed(request);
            }
        });

        mBuilder.client(mHttpClient.build());
        return this;
    }

    public <S> S buildForService(Class<S> ServiceClass) {
        Retrofit retrofit = mBuilder.build();
        return retrofit.create(ServiceClass);
    }
}