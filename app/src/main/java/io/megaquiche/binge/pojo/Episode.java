package io.megaquiche.binge.pojo;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Timo Maemecke (@timomeh) on 29/05/16.
 * <p/>
 * TODO: Add a class header comment
 */
public class Episode {
    @SerializedName("episode_number") private int mNumber;
    @SerializedName("name") private String mName;
    @SerializedName("id") private int mId;
    @SerializedName("season_number") private int mSeasonNumber;

    public Episode(int number, String name, int id, int seasonNumber) {
        mNumber = number;
        mName = name;
        mId = id;
        mSeasonNumber = seasonNumber;
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
}
