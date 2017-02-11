package io.megaquiche.binge.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.megaquiche.binge.models.Episode;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by @timomeh on 29/05/16.
 */

public class Season extends RealmObject {

    @PrimaryKey
    @SerializedName("id") private int mId;

    @SerializedName("season_number") private int mNumber;
    @SerializedName("episodes") private List<Episode> mEpisodeList;
    @SerializedName("episode_count") private int mEpisodeCount;

    private boolean mCompleted;
    private Series mSeries;

    public Season() {
        mId = 0;
        mNumber = 0;
        mEpisodeList = null;
        mEpisodeCount = 0;
        mCompleted = false;
        mSeries = null;
    }

    public Season(int number, int id, List<Episode> episodeList, int episodeCount, boolean completed, Series series) {
        mNumber = number;
        mId = id;
        mEpisodeList = episodeList;
        mEpisodeCount = episodeCount;
        mSeries = series;
        mCompleted = completed;
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

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }

    // Series has intentionally no getter/setter, because it's managed by Realm.

}
