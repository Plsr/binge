package io.megaquiche.binge.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by @timomeh on 29/05/16.
 */


public class Episode extends RealmObject {

    @SerializedName("id")
    @PrimaryKey
    private int mId;

    @SerializedName("episode_number") private int mNumber;
    @SerializedName("name") private String mName;
    @SerializedName("season_number") private int mSeasonNumber;

    private boolean mWatched;
    private Season mSeason;

    public Episode(int number, String name, int id, int seasonNumber, boolean watched, Season season) {
        mNumber = number;
        mName = name;
        mId = id;
        mSeasonNumber = seasonNumber;
        mWatched = watched;
        mSeason = season;
    }

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int number) {
        mNumber = number;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getSeasonNumber() {
        return mSeasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        mSeasonNumber = seasonNumber;
    }

    public String getEpisodeCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("S");
        if (mSeasonNumber < 10) {
            sb.append("0");
        }
        sb.append(mSeasonNumber);
        sb.append("E");
        if (mNumber < 10) {
            sb.append("0");
        }
        sb.append(mNumber);
        return sb.toString();
    }

    public boolean isWatched() {
        return mWatched;
    }

    public void setWatched(boolean watched) {
        mWatched = watched;
    }

    // Season has intentionally no getter/setter, because it's managed by Realm.

}
