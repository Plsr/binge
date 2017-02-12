package io.megaquiche.binge;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

import io.megaquiche.binge.data.SeriesService;
import io.megaquiche.binge.helpers.MockSeriesService;
import io.megaquiche.binge.models.SearchResults;
import io.megaquiche.binge.models.Season;
import io.megaquiche.binge.models.Series;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by @timomeh on 07/02/2017.
 */

public class SeriesServiceTest {
    SeriesService mSeriesService;
    MockSeriesService mMockSeriesService;

    @Before
    public void initialize() {
        mMockSeriesService = new MockSeriesService();
        MockWebServer mock = mMockSeriesService.getMockWebServer();
        mSeriesService = new SeriesService(mock.url("").toString());
    }

    @After
    public void closeServer() {
        try {
            mMockSeriesService.getMockWebServer().shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void seriesService_searchSeries_returnsSearchResult() throws IOException {
        Call<SearchResults> call = mSeriesService.getService().searchSeries("doctor who", 1);
        Response<SearchResults> response = call.execute();
        SearchResults searchResults = response.body();

        assertEquals(searchResults.getPage(), 1);
        assertEquals(searchResults.getSeriesList().size(), 2);
        assertEquals(searchResults.getSeriesList().get(0).getName(), "Doctor Who");
    }

    @Test
    public void seriesService_getSeries_returnsSeries() throws IOException {
        Call<Series> call = mSeriesService.getService().getSeries(1100);
        Response<Series> response = call.execute();
        Series series = response.body();

        assertEquals(series.getId(), 1100);
        assertEquals(series.getName(), "How I Met Your Mother");
    }

    @Test
    public void seriesService_getSeason_returnsSeason() throws IOException {
        Call<Season> call = mSeriesService.getService().getSeason(1100, 1);
        Response<Season> response = call.execute();
        Season season = response.body();

        assertEquals(season.getNumber(), 1);
    }
}
