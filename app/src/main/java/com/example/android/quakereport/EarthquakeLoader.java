package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ivandokov on 2/16/17.
 */

public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {

    private String url;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Earthquake> loadInBackground() {

        if (url == null) {
            return null;
        }

        ArrayList<Earthquake> result = new ArrayList<>();
        try {
            String jsonString = QueryUtils.makeHttpRequest(QueryUtils.createUrl(url));
            result = QueryUtils.extractEartquakes(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
