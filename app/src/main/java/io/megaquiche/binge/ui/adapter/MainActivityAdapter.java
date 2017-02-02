package io.megaquiche.binge.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.megaquiche.binge.R;
import io.megaquiche.binge.pojo.SeriesDummy;
import io.megaquiche.binge.pojo.Series;

/**
 * Adapter for the RecyclerView in the MainActivity.
 * All very straight forward.
 *
 * Created by plsr on 29/05/16.
 */
public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {

    public static final String TAG = "MainActivityAdapter";

    public interface SeriesAdapterInterface {
        void onItemClick(int position);
    }

    private Context mContext;
    private List<Series> mSeries;
    private SeriesAdapterInterface mInterface;

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView seriesCardView;
        ImageView titleImage;
        TextView seriesTitle;
        TextView episodeTitle;
        TextView episodeNumber;
        ImageButton actionMarkWatched;

        public ViewHolder(View v) {
            super(v);
            seriesCardView = (CardView) v.findViewById(R.id.series_card);
            titleImage = (ImageView) v.findViewById(R.id.title_image);
            seriesTitle = (TextView) v.findViewById(R.id.series_title);
            episodeTitle = (TextView) v.findViewById(R.id.episode_title);
            episodeNumber = (TextView) v.findViewById(R.id.episode_number);
            actionMarkWatched = (ImageButton) v.findViewById(R.id.action_mark_watched);

        }
    }

    public MainActivityAdapter (Context context, List<Series> series, SeriesAdapterInterface rInterface) {
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
    public void onBindViewHolder(MainActivityAdapter.ViewHolder holder, final int position) {
        final Series series = mSeries.get(position);
        CardView seriesCardView = (holder).seriesCardView;
        ImageView titleImage = (holder).titleImage;
        TextView seriesTitle = (holder).seriesTitle;
        TextView episodeTitle = (holder).episodeTitle;
        TextView episodeNumber = (holder).episodeNumber;
        ImageButton actionMarkWatched = (holder).actionMarkWatched;

        // TODO: Get rest of data
        //titleImage.setImageDrawable(series.get());
        seriesTitle.setText(series.getName());
        //episodeTitle.setText(series.get());
        //episodeNumber.setText(series.getEpisodeCount());

        // onClickListerner for ImageButton
        actionMarkWatched.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // TODO: Mark latest unseen epsiode as seen and display next unseen Episode
                Log.d(TAG, "actionMarkWatched onClick listener called in position " + position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSeries.size();
    }



}
