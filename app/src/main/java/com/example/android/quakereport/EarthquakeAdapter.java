package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by ivandokov on 2/9/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

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

        Earthquake earthquake = earthquakes.get(position);

        TextView magnituteView = (TextView) listItemView.findViewById(R.id.magnitude);
        GradientDrawable magnitudeCircle = (GradientDrawable) magnituteView.getBackground();
        int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);
        magnituteView.setText(String.valueOf(formatMagnitude(earthquake.getMagnitude())));

        handleLocation(earthquake, listItemView);

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD, yyyy");
        dateView.setText(String.valueOf(dateFormat.format(earthquake.getTime())));

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        timeView.setText(String.valueOf(timeFormat.format(earthquake.getTime())));

        return listItemView;
    }

    private void handleLocation(Earthquake earthquake, View view) {
        TextView primaryLocation = (TextView) view.findViewById(R.id.location);
        TextView locationOffset = (TextView) view.findViewById(R.id.location_offset);

        String originalLocation = earthquake.getLocation();

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String [] locationSplitString = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset.setText(locationSplitString[0] + LOCATION_SEPARATOR);
            primaryLocation.setText(locationSplitString[1]);
        } else {
            locationOffset.setText(getContext().getString(R.string.near_the));
            primaryLocation.setText(originalLocation);
        }
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    public void setEarthquakes(ArrayList<Earthquake> earthquakes) {
        this.earthquakes = earthquakes;
    }

    @Override
    public int getCount() {
        return this.earthquakes.size();
    }
}
