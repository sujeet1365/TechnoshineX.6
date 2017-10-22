package com.example.viking.tsx6;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.viking.tsx6.Fragments.Sponsors;

/**
 * Created by viking on 21/7/16.
 */
public class Sponsors_Adapter extends RecyclerView.Adapter<Sponsors_Adapter.ViewHolder> {
    private int[] imglist;
    Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public CardView cardView;

        //  public TextView textView;

        public ViewHolder(View itmView) {
            super(itmView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view_sponsors);
            cardView = (CardView) itemView.findViewById(R.id.card_view_sponsors);
        }

    }
    public Sponsors_Adapter(int[] contacts) {
        imglist = contacts;
    }
    @Override
    public void onBindViewHolder(Sponsors_Adapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ImageView im=viewHolder.imageView;



        // Set item views based on the data model

        im.setImageBitmap(decodeSampledBitmap(context.getResources(), imglist[position], 200, 200));

    }
    @Override
    public Sponsors_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.cardview_sponsors, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }
    // Return the total count of items
    @Override
    public int getItemCount() {
        return imglist.length;
    }

    public static Bitmap decodeSampledBitmap(Resources res,int resId,int reqwidth,int reqheight)
    {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res,resId,options);

        options.inSampleSize = calculate_image_size(options,reqwidth,reqheight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res,resId,options);
    }
    public static int calculate_image_size(BitmapFactory.Options options,int reqwidth,int reqheight)
    {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if(height>reqheight || width>reqwidth)
        {
            final int halfHeight = height/2;
            final int halfWidth = width/2;

            while((halfHeight/inSampleSize)>=reqheight && (halfWidth/inSampleSize)>= reqwidth)
            {
                inSampleSize=inSampleSize*2;
            }
        }
        return inSampleSize;
    }
}
