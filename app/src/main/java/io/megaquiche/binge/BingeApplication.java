package io.megaquiche.binge;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by @timomeh on 11/02/2017.
 */

public class BingeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Realm Database for whole Application
        Realm.init(this);
    }
}
