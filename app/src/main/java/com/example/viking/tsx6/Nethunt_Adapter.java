package com.example.viking.tsx6;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by viking on 24/8/16.
 */
public class Nethunt_Adapter extends RecyclerView.Adapter<Nethunt_Adapter.ViewHolder> {
    private int[] imglist;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView_Instruction,instruction_1,instruction_2,instruction_3,instruction_4;

        public ViewHolder(View itmView) {
            super(itmView);
            imageView = (ImageView) itemView.findViewById(R.id.nethunt_img);
            textView_Instruction= (TextView) itemView.findViewById(R.id.instruction_text);
            instruction_1= (TextView) itemView.findViewById(R.id.instruction_1);
            instruction_2= (TextView) itemView.findViewById(R.id.instruction_2);
            instruction_3= (TextView) itemView.findViewById(R.id.instruction_3);
            instruction_4= (TextView) itemView.findViewById(R.id.instruction_4);
        }

    }
    public Nethunt_Adapter(int[] contacts) {
        imglist = contacts;
    }
    @Override
    public void onBindViewHolder(Nethunt_Adapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ImageView im=viewHolder.imageView;
        TextView tx2 = viewHolder.textView_Instruction;
        TextView instruction_1 = viewHolder.instruction_1;
        TextView instruction_2 = viewHolder.instruction_2;
        TextView instruction_3 = viewHolder.instruction_3;
        TextView instruction_4 = viewHolder.instruction_4;
        tx2.setTypeface(MainActivity.typeface);
        instruction_1.setTypeface(MainActivity.typeface_3);
        instruction_2.setTypeface(MainActivity.typeface_3);
        instruction_3.setTypeface(MainActivity.typeface_3);
        instruction_4.setTypeface(MainActivity.typeface_3);
        // Set item views based on the data model
        im.setImageResource(imglist[position]);
        im.setImageBitmap(decodeSampledBitmap(context.getResources(), imglist[position], 200, 100));
        BitmapDrawable drawable = (BitmapDrawable) im.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        Bitmap blurred = MainActivity.blurRenderScript(bitmap, 25);//second parameter is radius
        //im.setImageBitmap(blurred);

    }
    @Override
    public Nethunt_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.cardview_nethunt, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }
    // Return the total count of items
    @Override
    public int getItemCount() {
        return imglist.length;
    }
    public static Bitmap decodeSampledBitmap(Resources res, int resId, int reqwidth, int reqheight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculate_image_size(options, reqwidth, reqheight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculate_image_size(BitmapFactory.Options options, int reqwidth, int reqheight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqheight || width > reqwidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqheight && (halfWidth / inSampleSize) >= reqwidth) {
                inSampleSize = inSampleSize * 2;
            }
        }
        return inSampleSize;
    }
}