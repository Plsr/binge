package io.megaquiche.binge.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.megaquiche.binge.models.Season;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by @timomeh on 27/05/16.
 */

public class Series extends RealmObject {

    public static final String TMDB_BACKDROP_PREFIX = "http://image.tmdb.org/t/p/w780";

    @PrimaryKey
    @SerializedName("id")
    private int mId;

    @SerializedName("name") private String mName;
    @SerializedName("original_name") private String mOriginalName;
    @SerializedName("backdrop_path") private String mBackdropUrl;
    @SerializedName("poster_path") private String mPosterUrl;
    @SerializedName("overview") private String mDescription;
    @SerializedName("seasons") private List<Season> mSeasons;

    public Series(String name, String originalName, int id, String backdropUrl, String posterUrl, String description, List<Season> seasons) {
        mName = name;
        mOriginalName = originalName;
        mId = id;
        mBackdropUrl = backdropUrl;
        mPosterUrl = posterUrl;
        mDescription = description;
        mSeasons = seasons;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getOriginalName() {
        return mOriginalName;
    }

    public void setOriginalName(String originalName) {
        mOriginalName = originalName;
    }

    public String getBackdropUrl() {
        return TMDB_BACKDROP_PREFIX + mBackdropUrl;
    }

    public void setBackdropUrl(String backdropUrl) {
        mBackdropUrl = backdropUrl;
    }

    public String getPosterUrl() {
        return mPosterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        mPosterUrl = posterUrl;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public List<Season> getSeasons() {
        return mSeasons;
    }

    public void setSeasons(List<Season> seasons) {
        mSeasons = seasons;
    }

}
