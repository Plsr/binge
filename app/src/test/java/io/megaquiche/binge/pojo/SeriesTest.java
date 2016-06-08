package io.megaquiche.binge.pojo;

import com.google.gson.*;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Timo Maemecke (@timomeh) on 01/06/16.
 * <p/>
 * TODO: Add a class header comment
 */
public class SeriesTest {
    @Test
    public void parsing_withoutSeasons_isWorking() throws Exception {
        String json = "{'name': 'Series Name', 'id': 941, 'overview': 'This is a description', "+
                "'backdrop_path': '/image.jpg'}";
        Gson gson = new Gson();
        Series series = gson.fromJson(json, Series.class);

        assertEquals(941, series.getId());
        assertEquals("Series Name", series.getName());
        assertEquals("This is a description", series.getDescription());
        assertEquals(Series.TMDB_BACKDROP_PREFIX + "/image.jpg", series.getImageUrl());
        assertNull(series.getSeasons());
    }
}
