package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<com.example.android.quakereport.Earthquake> {
    /**
     * Create a new {@link EarthquakeAdapter} object
     *
     * @param context     is the current context (i.e. Activity) that the adapater is being cread in respect of
     * @param earthquakes is the list of (@link Earthquake)s to be displayed.
     */
    public EarthquakeAdapter(Activity context, List<Earthquake> earthquakes) {
        //  Here, we initialize the Array Adapter's internal storage for the context and the list.
        //  the second argument is used when the ArrayAdapter is populating a single TextView
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakes);

    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The AdapterView position that is requesting a view
     * @param convertView The recycled view to populate
     *                    (search online for android view recycling for more info)
     * @param parent      The parent ViewGroup that is used for inflation
     * @return The View for the position in the AdapterView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        com.example.android.quakereport.Earthquake currentEarthquake = getItem(position);

        //  Find the TextView in the list_view.xml layout with the ID quakeLocationTextView
        TextView quakeLocationTextView = (TextView) listItemView.findViewById(R.id.quakeLocationTextView);

        //  Find the TextView in the list_view.xml layout with the ID quakeDistanceTextView
        TextView quakeDistanceTextView = (TextView) listItemView.findViewById(R.id.quakeDistanceTextView);


        //  Get the Earthquake Name from the current Earthquake object and set to variable mainString
        String mainString = currentEarthquake.getQuakeName();


        // Create locatingString & distanceString
        String distanceString;
        String locationString;


        // Split the mainString into two sub-strings, distanceString & locationString depending on whether "of" is present in mainString
        if (mainString.contains("of")) {
            locationString = mainString.substring(mainString.indexOf("of") + 3, mainString.length());
            distanceString = mainString.substring(0, mainString.indexOf("of") + 2);
        } else {
            locationString = mainString;
            distanceString = "Near the";
        }

        // Set this text on the new ListView
        quakeLocationTextView.setText(locationString);
        quakeDistanceTextView.setText(distanceString);


        //  Find the TextView in the list_view.xml layout with the ID magnitudeTextView
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitudeTextView);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor((int)currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        //  Get the Earthquake Magnitude from the current Earthquake object & Format Magnitude Data
        DecimalFormat formatter = new DecimalFormat("##.#");
        String output = formatter.format(currentEarthquake.getMagnitude());

        //  Set this text on the new ListView
        magnitudeTextView.setText(output);

        //  Find the TextView in the list_view.xml layout with the ID quakeDateTextView
        TextView quakeDateTextView = (TextView) listItemView.findViewById(R.id.quakeDateTextView);

        //  Find the TextView in the list_view.xml layout with the ID quakeTimeTextView
        TextView quakeTimeTextView = (TextView) listItemView.findViewById(R.id.quakeTimeTextView);

        //  Define required Date and Time formats
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");

        //  Get the Earthquake Date from the current Earthquake object and break apart to specific required formats
        quakeTimeTextView.setText(timeFormat.format(currentEarthquake.getQuakeDate()));
        quakeDateTextView.setText(dateFormat.format(currentEarthquake.getQuakeDate()));

        // Return the whole list item layout (containing 3 TextViews) so that it can be displayed
        return listItemView;

    }

    public int getMagnitudeColor(int magnitude){

        switch(magnitude) {
            case 1:
                return ContextCompat.getColor(getContext(),R.color.magnitude1);

            case 2:
                return ContextCompat.getColor(getContext(),R.color.magnitude2);

            case 3:
                return ContextCompat.getColor(getContext(),R.color.magnitude3);

            case 4:
                return ContextCompat.getColor(getContext(),R.color.magnitude4);

            case 5:
                return ContextCompat.getColor(getContext(),R.color.magnitude5);

            case 6:
                return ContextCompat.getColor(getContext(),R.color.magnitude6);

            case 7:
                return ContextCompat.getColor(getContext(),R.color.magnitude7);

            case 8:
                return ContextCompat.getColor(getContext(),R.color.magnitude8);

            case 9:
                return ContextCompat.getColor(getContext(),R.color.magnitude9);

            case 10:
                return ContextCompat.getColor(getContext(),R.color.magnitude10plus);

            default:
                return ContextCompat.getColor(getContext(),R.color.magnitude10plus);
        }
    }
}
