package android.inmobi.com.directme;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepak.jha on 2/21/16.
 */
public class RestaurantSearchResult extends ListActivity {

    public String latfrmobj;
    public String lonfrmobj;
    public ArrayList<String> lati;
    public ArrayList<String> longi, restaurantsName;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView test = (ListView) findViewById(R.id.listview);
        setListAdapter(null);

        restaurantsName = (ArrayList<String>) getIntent().getStringArrayListExtra("restaurantnames");
        ArrayList<String> calcdistance = (ArrayList<String>) getIntent().getStringArrayListExtra("distance");
        ArrayList<String> calcpopulation = (ArrayList<String>) getIntent().getStringArrayListExtra("population");
        lati = (ArrayList<String>) getIntent().getStringArrayListExtra("lat");
        longi = (ArrayList<String>) getIntent().getStringArrayListExtra("lng");

        latfrmobj = getIntent().getExtras().getString("latitude");
        lonfrmobj = getIntent().getExtras().getString("longitude");
        System.out.println("latitude is"+latfrmobj);
        System.out.println("longitude is is"+lonfrmobj);


        setListAdapter(new RestArrayAdapter(this.getBaseContext(), restaurantsName, calcdistance, calcpopulation));
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        //get selected items
        String selectedValue = (String) getListAdapter().getItem(position);
        try {
            PackageManager pm = getPackageManager();
            pm.getPackageInfo("com.ubercab", PackageManager.GET_ACTIVITIES);
            String uri = "uber://?action=setPickup&&pickup[latitude]=" +latfrmobj + "&pickup[longitude]=" + lonfrmobj + "&dropoff[latitude]="+ lati.get(position) +"&dropoff[longitude]="+longi.get(position)+"&pickup[nickname]=mylocation&dropoff[nickname]="+restaurantsName.get(position);
            System.out.println("URI is : "+uri);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(uri));
            startActivity(intent);
        } catch (PackageManager.NameNotFoundException e) {
            // No Uber app! Open Mobile Website.
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.uber.com/sign-up?client_id=YOUR_CLIENT_ID"));
            startActivity(browserIntent);
        }
        //Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();

    }

}