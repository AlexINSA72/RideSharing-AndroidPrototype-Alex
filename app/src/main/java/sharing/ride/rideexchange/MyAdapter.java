package sharing.ride.rideexchange;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    private String[] mDataset;
    private RideListings rl;
    private boolean destination;

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
    public MyAdapter(String[] myDataset, RideListings rl, boolean destination) {
        mDataset = myDataset;
        this.rl = rl;
        this.destination = destination;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.ride_listing_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.origin.setText(mDataset[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = mDataset[position];
                System.out.println("Clicked: " + id);

                if(destination)
                {
                    rl.selectedDes = mDataset[position];
                }

                else
                {
                    rl.selectedDep = mDataset[position];
                }

                System.out.println("DEP: " + rl.selectedDep + " DES: " + rl.selectedDes);
                //You can call detail fragment here
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
