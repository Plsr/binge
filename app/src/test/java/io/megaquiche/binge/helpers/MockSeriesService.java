package io.megaquiche.binge.helpers;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

/**
 * Created by @timomeh on 07/02/2017.
 */

public class MockSeriesService {
    MockWebServer mMockWebServer;

    public MockSeriesService() {
        mMockWebServer = new MockWebServer();

        final Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                if (request.getPath().contains("search/tv")) {
                    return new MockResponse()
                            .setResponseCode(200)
                            .setBody(readFile("search_series_success.json"));
                }

                if (matchesPath("tv/[0-9]+/season/[0-9]+", request.getPath())) {
                    return new MockResponse()
                            .setResponseCode(200)
                            .setBody(readFile("single_season_success.json"));
                }

                if (matchesPath("tv/[0-9]", request.getPath())) {
                    return new MockResponse()
                            .setResponseCode(200)
                            .setBody(readFile("single_series_success.json"));
                }

                return new MockResponse().setResponseCode(404);
            }
        };

        mMockWebServer.setDispatcher(dispatcher);

    }

    public MockWebServer getMockWebServer() {
        return mMockWebServer;
    }

    private String readFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File f = new File(classLoader.getResource(fileName).getFile());
        String content = null;
        try {
            content = new Scanner(f).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }

    private boolean matchesPath(String needle, String path) {
        return Pattern.compile(needle).matcher(path).find();
    }
}
