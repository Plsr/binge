package io.megaquiche.binge.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.megaquiche.binge.models.Episode;

/**
 * Created by Timo Maemecke (@timomeh) on 29/05/16.
 * <p/>
 * TODO: Add a class header comment
 */
public class Season {
    @SerializedName("season_number") private int mNumber;
    @SerializedName("id") private int mId;
    @SerializedName("episodes") private List<Episode> mEpisodeList;
    @SerializedName("episode_count") private int mEpisodeCount;

    public Season(int number, int id, List<Episode> episodeList, int episodeCount) {
        mNumber = number;
        mId = id;
        mEpisodeList = episodeList;
        mEpisodeCount = episodeCount;
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

    public int getEpisodeCount() {
        return mEpisodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        mEpisodeCount = episodeCount;
    }
}
