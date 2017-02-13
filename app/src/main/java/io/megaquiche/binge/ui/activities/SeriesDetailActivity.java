package io.megaquiche.binge.ui.activities;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        // Set up the Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        // Check if actionBar != null to evade the compiler warning
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        Intent intent = getIntent();
        SeriesDummy seriesDummy =  intent.getParcelableExtra("SERIES_DUMMY");
        Log.d(TAG, seriesDummy.getSeriesTitle()); // DEBUG

        TextView seriesTitle = (TextView) findViewById(R.id.series_title);
        ImageView titleImage = (ImageView) findViewById(R.id.listview_title_image);

        seriesTitle.setText(seriesDummy.getSeriesTitle());
        titleImage.setImageDrawable(getDrawable(seriesDummy.getTitleImageIdentifier()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            // Respond to the overflow menu icon
            case R.id.action_delete:
                // TODO: Delete current season
                return true;
            case R.id.action_refresh:
                // TODO: Refresh current series
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }
}
