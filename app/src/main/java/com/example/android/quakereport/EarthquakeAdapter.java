package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by ivandokov on 2/9/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
        this.earthquakes = earthquakes;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        Earthquake earthquake = getItem(position);

        TextView magnitute = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitute.setText(String.valueOf(earthquake.getMagnitude()));

        handleLocation(earthquake, listItemView);

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD, yyyy");
        date.setText(String.valueOf(dateFormat.format(earthquake.getTime())));

        TextView time = (TextView) listItemView.findViewById(R.id.time);
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        time.setText(String.valueOf(timeFormat.format(earthquake.getTime())));

        return listItemView;
    }

    private void handleLocation(Earthquake earthquake, View view) {
        TextView location = (TextView) view.findViewById(R.id.location);
        TextView distance = (TextView) view.findViewById(R.id.distance);

        String locationString = earthquake.getLocation();
        if (locationString.indexOf(" of ") != -1) {
            int splitIndex = locationString.indexOf(" of ") + 4;
            distance.setText(locationString.substring(0, splitIndex));
            location.setText(locationString.substring(splitIndex, locationString.length()));
        } else {
            distance.setText(getContext().getString(R.string.near_the));
            location.setText(locationString);
        }
    }
}
