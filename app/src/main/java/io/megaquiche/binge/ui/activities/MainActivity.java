package io.megaquiche.binge.ui.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import io.megaquiche.binge.R;
import io.megaquiche.binge.pojo.SeriesDummy;
import io.megaquiche.binge.ui.adapter.MainActivityAdapter;

public class MainActivity extends AppCompatActivity implements MainActivityAdapter.SeriesAdapterInterface {

    private static final String TAG = "MainActivity";
    private Toolbar toolbar;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.activity_main_title);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        
        if(actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        // Set StatusBar Color
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.color_primary_dark));

        mRecyclerView = (RecyclerView) findViewById(R.id.activity_main_recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<SeriesDummy> seriesList  = createDummyData();
        mAdapter = new MainActivityAdapter(this, seriesList, this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home, menu);

        return true;
    }

    private List<SeriesDummy> createDummyData() {

        List<SeriesDummy> dummyList = new ArrayList<SeriesDummy>();

        SeriesDummy bigBangTheory = new SeriesDummy(getDrawable(R.drawable.placeholder_bbt_title),
                "The Big Bang Theory", "Doktor Proton", "S06E08");

        SeriesDummy greysAnatomy = new SeriesDummy(getDrawable(R.drawable.placeholder_ga_title),
                "Greys Anatomy", "Ach Oh Gott die Liebe", "S18E02");

        SeriesDummy betterCallSaul = new SeriesDummy(getDrawable(R.drawable.placeholder_bcs_title),
                "Better Call Saul", "Tuco", "S01E06");

        dummyList.add(bigBangTheory);
        dummyList.add(greysAnatomy);
        dummyList.add(betterCallSaul);

        return dummyList;
    }

    @Override
    public void onItemClick(int position) {
        // TODO: Do things
    }
}
