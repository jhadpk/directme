package android.inmobi.com.directme;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by deepak.jha on 2/21/16.
 */
public class RestArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> restaurants;
    private final ArrayList<String> calcdistance;
    private final ArrayList<String> calcpopulation;

    public RestArrayAdapter(Context context, ArrayList<String> restaurants, ArrayList<String> calcdistance, ArrayList<String> calcpopulation) {
        super(context, R.layout.listrestaurants, restaurants);
        this.context = context;
        this.restaurants = restaurants;
        this.calcdistance = calcdistance;
        this.calcpopulation = calcpopulation;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.listrestaurants, parent, false);
        TextView restaurantName = (TextView) rowView.findViewById(R.id.restaurantname);
        TextView distance = (TextView) rowView.findViewById(R.id.distance);
        TextView population = (TextView) rowView.findViewById(R.id.population);


        restaurantName.setText(restaurants.get(position));
        distance.setText("Distance: "+calcdistance.get(position)+"km");
        population.setText("Checkins : "+calcpopulation.get(position));
        return rowView;
    }
}
