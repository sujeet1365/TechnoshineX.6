package com.example.viking.tsx6;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by viking on 21/7/16.
 */
public class Contact_Adapter extends
        RecyclerView.Adapter<Contact_Adapter.ViewHolder>
{

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        //  public TextView textView;



        public ViewHolder(View itmView) {
            super(itmView);


        }

    }

    // Pass in the contact array into the constructor
    public Contact_Adapter(){
    }

    @Override
    public Contact_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.cardview_contact, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }


    // Return the total count of items
    @Override
    public int getItemCount() {
        return 1;
    }

}
