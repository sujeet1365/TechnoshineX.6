package com.example.viking.tsx6;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by viking on 21/7/16.
 */
public class Offline_Adapter extends RecyclerView.Adapter<Offline_Adapter.ViewHolder> {

    public Context context;
    private int[] imglist,titlelist,contentlist,tag_line,poster;
    public View contact;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView card_title,card_content;
        public Button button;
        public CardView cardView;
        //  public TextView textView;

        public ViewHolder(View itmView) {
            super(itmView);

            imageView = (ImageView) itemView.findViewById(R.id.img);
            card_title = (TextView) itemView.findViewById(R.id.card_title);
            card_title.setTypeface(MainActivity.typeface);
            card_content = (TextView) itemView.findViewById(R.id.card_content);
            card_content.setTypeface(MainActivity.typeface);
            button = (Button) itemView.findViewById(R.id.card_button);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }

    }
    public Offline_Adapter(int[] contacts,int[] title,int[] tagline,int[] content,int[] post) {
        imglist = contacts;
        titlelist = title;
        contentlist = content;
        tag_line = tagline;
        poster = post;

    }
    @Override
    public void onBindViewHolder(Offline_Adapter.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        ImageView im=viewHolder.imageView;
        im.setImageBitmap(decodeSampledBitmap(context.getResources(),imglist[position],200,200));

        TextView tx1 = viewHolder.card_title;
        TextView tx2 = viewHolder.card_content;
        tx1.setText(titlelist[position]);
        tx2.setText(tag_line[position]);
        tx1.setTypeface(MainActivity.typeface);
        tx2.setTypeface(MainActivity.typeface_3);

        CardView cardView = viewHolder.cardView;
        final int[] back_col = context.getResources().getIntArray(R.array.offline);
        cardView.setCardBackgroundColor(back_col[position]);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context, R.style.DialogAnimation);
                dialog.setContentView(R.layout.for_dialog_new);
                ImageView imageView = (ImageView) dialog.findViewById(R.id.background_image);

                TextView textView = (TextView) dialog.findViewById(R.id.detailTitle);
                TextView title = (TextView) dialog.findViewById(R.id.title);

                title.setText(titlelist[position]);
                title.setTypeface(Splash_screen.typeface);
                textView.setText(contentlist[position]);
                textView.setTypeface(MainActivity.typeface_3);

                imageView.setImageBitmap(decodeSampledBitmap(context.getResources(), poster[position], 200, 200));

                ImageView imageView_background = (ImageView) dialog.findViewById(R.id.background_dialog);
                imageView_background.setImageBitmap(decodeSampledBitmap(context.getResources(), poster[position], 200, 100));
                dialog.getWindow().setFormat(PixelFormat.TRANSLUCENT);
                dialog.show();
            }
        });
    }
    @Override
    public Offline_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contactView;
        LayoutInflater inflater;
        ViewHolder viewHolder=null;

        switch (viewType) {
            case 0:
                context = parent.getContext();
                inflater = LayoutInflater.from(context);

//             Inflate the custom layout
                contactView = inflater.inflate(R.layout.card_view, parent, false);
                contactView.requestLayout();


                // Return a new holder instance
                viewHolder = new ViewHolder(contactView);
                break;
            case 1:
                context = parent.getContext();
                inflater = LayoutInflater.from(context);

//             Inflate the custom layout
                contactView = inflater.inflate(R.layout.cardview_right, parent, false);
                contactView.requestLayout();


                // Return a new holder instance
                viewHolder = new ViewHolder(contactView);
                break;

        }
        return viewHolder;
    }
    // Return the total count of items
    @Override
    public int getItemCount() {
        return imglist.length;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
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
