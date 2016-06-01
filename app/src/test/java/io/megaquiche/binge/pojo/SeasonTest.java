package io.megaquiche.binge.pojo;

import com.google.gson.*;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Timo Maemecke (@timomeh) on 01/06/16.
 * <p/>
 * TODO: Add a class header comment
 */
public class SeasonTest {
    @Test
    public void parsing_withoutEpisodes_isWorking() throws Exception {
        String json = "{'season_number': 1, 'id': 941}";
        Gson gson = new Gson();
        Season season = gson.fromJson(json, Season.class);

        assertEquals(1, season.getNumber());
        assertEquals(941, season.getId());
        assertNull(season.getEpisodes());
    }

    @Test
    public void parsing_withEpisodes_isWorking() throws Exception {
        String json = "{'season_number': 1, 'id': 941, 'episodes': ["+
                "{'episode_number': 1, 'name': 'Some Name', 'id': 15, 'season_number': 1},"+
                "{'episode_number': 2, 'name': 'Other Name', 'id': 16, 'season_number': 1}"+
                "]}";
        Gson gson = new Gson();
        Season season = gson.fromJson(json, Season.class);

        assertEquals(1, season.getNumber());
        assertEquals(941, season.getId());
        assertEquals(2, season.getEpisodes().size());

        assertEquals(1, season.getEpisodes().get(0).getNumber());
        assertEquals("Some Name", season.getEpisodes().get(0).getName());
        assertEquals(15, season.getEpisodes().get(0).getId());
        assertEquals(1, season.getEpisodes().get(0).getSeasonNumber());

        assertEquals(2, season.getEpisodes().get(1).getNumber());
        assertEquals("Other Name", season.getEpisodes().get(1).getName());
        assertEquals(16, season.getEpisodes().get(1).getId());
        assertEquals(1, season.getEpisodes().get(1).getSeasonNumber());
    }


}
