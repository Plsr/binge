package io.megaquiche.binge.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Timo Maemecke (@timomeh) on 02/06/16.
 * <p/>
 * TODO: Add a class header comment
 */
public class SearchResults {
    @SerializedName("page") private int mPage;
    @SerializedName("total_pages") private int mLastPage;
    @SerializedName("results") private List<Series> mSeriesList;

    public SearchResults(int page, int lastPage, List<Series> seriesList) {
        mPage = page;
        mLastPage = lastPage;
        mSeriesList = seriesList;
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public int getLastPage() {
        return mLastPage;
    }

    public void setLastPage(int lastPage) {
        mLastPage = lastPage;
    }

    public List<Series> getSeriesList() {
        return mSeriesList;
    }

    public void setSeriesList(List<Series> seriesList) {
        mSeriesList = seriesList;
    }
}
