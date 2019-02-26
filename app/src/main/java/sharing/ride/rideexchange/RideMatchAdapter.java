package sharing.ride.rideexchange;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RideMatchAdapter extends RecyclerView.Adapter<RideMatchAdapter.MyViewHolder>
{
    //private String[] mDataset;
    private Ride[] rDataset;
    private GoogleMap mMap;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView origin;
        //public TextView destination;
        public MyViewHolder(View v) {
            super(v);
            origin = v.findViewById(R.id.rideOrigin);
            //destination = v.findViewById(R.id.rideDestination);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RideMatchAdapter(Ride[] myDataset, GoogleMap gMap) {
        this.rDataset = myDataset;
        this.mMap = gMap;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RideMatchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.ride_match_item, parent, false);
        RideMatchAdapter.MyViewHolder vh = new RideMatchAdapter.MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RideMatchAdapter.MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.origin.setText(rDataset[position].dep + " to " + rDataset[position].des + " " + rDataset[position].dayy + "/"
                + rDataset[position].monthh + "/" + rDataset[position].yearr + " at "
                + rDataset[position].hourr + ":" + rDataset[position].minss + "  " + rDataset[position].realNbPass + "/"
                + rDataset[position].nbPass + " places remaining with " + rDataset[position].name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ride id = rDataset[position];
                System.out.println("Clicked: " + id.name);
                mMap.clear();

//                LatLng sanFrancisco = new LatLng(37.773972, -122.431297);
//                LatLng losAngeles = new LatLng(34.052235, -118.243683);
//                LatLng sanLuisObispo = new LatLng(35.616348, -119.694298);

                LatLng departure = getCoords(id.dep);
                LatLng destination = getCoords(id.des);

                double xbs = (departure.latitude + destination.latitude) / 2.0;
                double ybs = (departure.longitude + destination.longitude) / 2.0;
                mMap.addMarker(new MarkerOptions().position(departure).title(id.dep));
                mMap.addMarker(new MarkerOptions().position(destination).title(id.des));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(xbs, ybs), 6.0f));
                //You can call detail fragment here
            }
        });
    }

    public LatLng getCoords(String s)
    {
        switch(s)
        {
            case "Los Angeles" :
                return new LatLng(34.052235, -118.243683);
            case "Santa Monica" :
                return new LatLng(34.024212, -118.496475);
            case "Westwood" :
                return new LatLng(34.052235, -118.243683);
            case "San Jose" :
                return new LatLng(37.279518, -121.867905);
            case "Oakland" :
                return new LatLng(37.804363, -122.271111);
            case "San Francisco" :
                return new LatLng(37.773972, -122.431297);
            default:
                return null;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return rDataset.length;
    }
}
