package io.megaquiche.binge.pojo;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Timo Maemecke (@timomeh) on 29/05/16.
 * <p/>
 * TODO: Add a class header comment
 */
public class Season {
    @SerializedName("season_number") private int mNumber;
    @SerializedName("id") private int mId;
    @SerializedName("episodes") private List<Episode> mEpisodeList;

    public Season(int number, int id, List<Episode> episodeList) {
        mNumber = number;
        mId = id;
        mEpisodeList = episodeList;
    }

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int number) {
        mNumber = number;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public List<Episode> getEpisodes() {
        return mEpisodeList;
    }

    public void setEpisodes(List<Episode> episodeList) {
        mEpisodeList = episodeList;
    }
}
