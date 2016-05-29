package io.megaquiche.binge.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.megaquiche.binge.R;
import io.megaquiche.binge.pojo.SeriesDummy;

/**
 * Created by Cheese on 29/05/16.
 */
public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {

    public interface SeriesAdapterInterface {
        void onItemClick(int position);
    }

    private Context mContext;
    private List<SeriesDummy> mSeries;
    private SeriesAdapterInterface mInterface;

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView seriesCardView;
        ImageView titleImage;
        TextView seriesTitle;
        TextView episodeTitle;
        TextView episodeNumber;

        public ViewHolder(View v) {
            super(v);
            seriesCardView = (CardView) v.findViewById(R.id.series_card);
            titleImage = (ImageView) v.findViewById(R.id.title_image);
            seriesTitle = (TextView) v.findViewById(R.id.series_title);
            episodeTitle = (TextView) v.findViewById(R.id.episode_title);
            episodeNumber = (TextView) v.findViewById(R.id.episode_number);

        }
    }

    public MainActivityAdapter (Context context, List<SeriesDummy> series, SeriesAdapterInterface rInterface) {
        mContext = context;
        mSeries = series;
        mInterface = rInterface;
    }


    @Override
    public MainActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_series, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainActivityAdapter.ViewHolder holder, int position) {
        final SeriesDummy series = mSeries.get(position);
        CardView seriesCardView = (holder).seriesCardView;
        ImageView titleImage = (holder).titleImage;
        TextView seriesTitle = (holder).seriesTitle;
        TextView episodeTitle = (holder).episodeTitle;
        TextView episodeNumber = (holder).episodeNumber;

        titleImage.setImageDrawable(series.getTitleImage());
        seriesTitle.setText(series.getSeriesTitle());
        episodeTitle.setText(series.getEpisodeTitle());
        episodeNumber.setText(series.getEpisodeCount());

    }

    @Override
    public int getItemCount() {
        return mSeries.size();
    }


}
