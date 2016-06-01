package io.megaquiche.binge.pojo;

import com.google.gson.Gson;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Timo Maemecke (@timomeh) on 01/06/16.
 * <p/>
 * TODO: Add a class header comment
 */
public class EpisodeTest {
    @Test
    public void parsing_isWorking() throws Exception {
        String json = "{'episode_number': 1, 'name': 'Some Name', 'id': 941, 'season_number': 2}";
        Gson gson = new Gson();
        Episode episode = gson.fromJson(json, Episode.class);

        assertEquals("Some Name", episode.getName());
        assertEquals(1, episode.getNumber());
        assertEquals(941, episode.getId());
        assertEquals(2, episode.getSeasonNumber());
        assertEquals("S02E01", episode.getEpisodeCode());
    }
}
