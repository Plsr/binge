package io.megaquiche.binge.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
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

    /**
     * Set the URL (host) of the foreign API.
     * This is a required step.
     *
     * @param url The baseURL of the API
     * @return The current RetrofitFactory
     */
    public RetrofitFactory addBaseUrl(String url) {
        mBuilder.baseUrl(url);
        return this;
    }

    /**
     * Add Support for Converting JSON to POJOs via GSON.
     *
     * @return The current RetrofitFactory
     */
    public RetrofitFactory addGsonSupport() {
        mBuilder.addConverterFactory(GsonConverterFactory.create());
        return this;
    }

    /**
     * Add Support for a connection reuse, e.g. HTTP/1.x keep-alive, SPDY, or HTTP/2.
     *
     * @return The current RetrofitFactory
     */
    public RetrofitFactory addReuseConnectionSupport() {
        ConnectionPool connectionPool = new ConnectionPool(1, 60, TimeUnit.SECONDS);
        mHttpClient.connectionPool(connectionPool);
        return this;
    }

    /**
     * Add a query parameter to every URL.
     *
     * @param key The query parameter's value
     * @param value The query parameter's value
     * @return The current RetrofitFactory
     */
    public RetrofitFactory addQueryParameterToUrl(final String key, final String value) {
        // Interject something into the Request
        mHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                // Get the original Request
                Request originalRequest = chain.request();

                // Take the Request's old url and build it with additional query parameters
                // to a new url
                HttpUrl newUrl = originalRequest.url()
                        .newBuilder()
                        .addQueryParameter(key, value)
                        .build();

                // Take the original Request and set its url to the new url
                Request interceptedRequest = originalRequest
                        .newBuilder()
                        .url(newUrl)
                        .build();

                // Add the new Request back to the client
                return chain.proceed(interceptedRequest);
            }
        });
        return this;
    }

    /**
     * Build RetrofitFactory to Retrofit based on previous methods.
     *
     * @param ServiceClass  Reference to the Class Object of the your Retrofit Interface
     * @return              The builded Retrofit Service
     */
    public <S> S buildForService(Class<S> ServiceClass) {
        // Build OkHttp Client
        OkHttpClient client = mHttpClient.build();

        // Add OkHttp Client to Retrofit
        mBuilder.client(client);

        // Finally, build Retrofit Client
        Retrofit retrofit = mBuilder.build();

        // Create Retrofit for specified Service
        return retrofit.create(ServiceClass);
    }

}