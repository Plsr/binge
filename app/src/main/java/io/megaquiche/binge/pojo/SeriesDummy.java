package io.megaquiche.binge.pojo;

import android.graphics.drawable.Drawable;
import android.media.Image;

/**
 * Created by Cheese on 29/05/16.
 */
public class SeriesDummy {

    Drawable titleImage;
    String seriesTitle;
    String episodeTitle;
    String episodeCount;



    public SeriesDummy(Drawable titleImage, String seriesTitle, String episodeTitle, String episodeCount) {
        this.titleImage = titleImage;
        this.seriesTitle = seriesTitle;
        this.episodeTitle = episodeTitle;
        this.episodeCount = episodeCount;
    }

    public Drawable getTitleImage() {
        return titleImage;
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
}
