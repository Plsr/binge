package io.megaquiche.binge.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Timo Maemecke (@timomeh) on 27/05/16.
 * <p/>
 * TODO: Add a class header comment
 */
public class Series {
    @SerializedName("seriesName") String mName;
    @SerializedName("overview") String mDescription;
}
