package io.megaquiche.binge.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cheese on 29/05/16.
 */
public class SeriesDummy implements Parcelable{

    int titleImageIdentifier;
    String seriesTitle;
    String episodeTitle;
    String episodeCount;



    public SeriesDummy(int titleImage,String drawableName, String seriesTitle, String episodeTitle, String episodeCount) {
        this.titleImageIdentifier = titleImage;
        this.seriesTitle = seriesTitle;
        this.episodeTitle = episodeTitle;
        this.episodeCount = episodeCount;


    }

    protected SeriesDummy(Parcel in) {
        titleImageIdentifier = in.readInt();
        seriesTitle = in.readString();
        episodeTitle = in.readString();
        episodeCount = in.readString();
    }

    public static final Creator<SeriesDummy> CREATOR = new Creator<SeriesDummy>() {
        @Override
        public SeriesDummy createFromParcel(Parcel in) {
            return new SeriesDummy(in);
        }

        @Override
        public SeriesDummy[] newArray(int size) {
            return new SeriesDummy[size];
        }
    };

    public int getTitleImageIdentifier() {
        return titleImageIdentifier;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public String getEpisodeTitle() {
        return episodeTitle;
    }

    public String getEpisodeCount() {
        return episodeCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(titleImageIdentifier);
        dest.writeString(seriesTitle);
        dest.writeString(episodeTitle);
        dest.writeString(episodeCount);
    }
}
