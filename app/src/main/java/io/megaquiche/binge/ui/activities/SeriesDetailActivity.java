package io.megaquiche.binge.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import io.megaquiche.binge.R;
import io.megaquiche.binge.pojo.SeriesDummy;

public class SeriesDetailActivity extends AppCompatActivity {

    private static final String TAG = "SeriesDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.series_detail);

        Intent intent = getIntent();
        SeriesDummy seriesDummy =  intent.getParcelableExtra("SERIES_DUMMY");
        Log.d(TAG, seriesDummy.getSeriesTitle()); // DEBUG

        TextView seriesTitle = (TextView) findViewById(R.id.series_title);
        ImageView titleImage = (ImageView) findViewById(R.id.listview_title_image);

        seriesTitle.setText(seriesDummy.getSeriesTitle());
        titleImage.setImageDrawable(getDrawable(seriesDummy.getTitleImageIdentifier()));
    }
}
