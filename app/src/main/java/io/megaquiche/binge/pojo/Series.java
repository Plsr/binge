package io.megaquiche.binge.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.megaquiche.binge.utils.Utils;

/**
 * Created by Timo Maemecke (@timomeh) on 27/05/16.
 * <p/>
 * TODO: Add a class header comment
 */
public class Series {
    public static final String TMDB_BACKDROP_PREFIX = "http://image.tmdb.org/t/p/w780";

    @SerializedName("name") private String mName;
    @SerializedName("id") private int mId;
    @SerializedName("backdrop_path") private String mImageUrl;
    @SerializedName("overview") private String mDescription;
    @SerializedName("seasons") private List<Season> mSeasons;

    public Series(String name, int id, String imageUrl, String description, List<Season> seasons) {
        mName = Utils.onNullEmptyString(name);
        mId = id;
        mImageUrl = imageUrl;
        mDescription = Utils.onNullEmptyString(description);
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

    public String getImageUrl() {
        return TMDB_BACKDROP_PREFIX + mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
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
