package com.example.viking.tsx6;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.AvoidXfermode;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viking.tsx6.Fragments.Config;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by viking on 21/7/16.
 */
public class About_Adapter extends
        RecyclerView.Adapter<About_Adapter.ViewHolder> {

    public static Context context;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public YouTubePlayerView youTubePlayerView;
        public TextView textView,schedule;
        public TextView date,place;

        public ViewHolder(View itmView) {
            super(itmView);
            textView=(TextView)itemView.findViewById(R.id.about);
            date=(TextView)itmView.findViewById(R.id.date);
            place=(TextView)itmView.findViewById(R.id.place);
            schedule=(TextView) itmView.findViewById(R.id.downloadSchedule);

        }

    }

    // Pass in the contact array into the constructor
    public About_Adapter(Context context){
        this.context=context;
    }

    @Override
    public About_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.cardview_about, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView textView = holder.textView;
        textView.setTypeface(Splash_screen.typeface_3);
        TextView date = holder.date;
        TextView place = holder.place;
        date.setTypeface(MainActivity.typeface_3);
        place.setTypeface(MainActivity.typeface_3);

//        holder.schedule.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                File file = new File("/sdcard/technoshineSchedule.png");
//                if (!file.exists()) {
//                    if (haveNetworkConnection()) {
//                        new DownloadImg().execute();
//                    } else {
//                        Toast.makeText(context, "Turn on Net Connection to download Schedule", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(context, "Schedule already downloaded @ " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });

    }

    private class DownloadImg extends AsyncTask<String, String, Bitmap> {

        private final ProgressDialog dialog_1 = new ProgressDialog(context);

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            dialog_1.setMessage("Downloading, please wait.......");
            dialog_1.show();
        }

        protected Bitmap doInBackground(String... args) {
            Bitmap myBitmap = null;
            try {
                URL url = new URL("http://cadnitd.co.in/images/nitlogo.png");
                //URL url = new URL(Config.SCHEDULE);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                myBitmap = BitmapFactory.decodeStream(input);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return myBitmap;
        }

        protected void onPostExecute(Bitmap mybitmap) {

            dialog_1.dismiss();
            String filename = String.valueOf(String.format("/sdcard/technoshineSchedule.jpg"));

            try {
                FileOutputStream stream = new FileOutputStream(Environment
                                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                                + filename);
               // java.io.File xmlFile = new java.io.File(Environment
                //        .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                //        + "/Filename.xml");

                ByteArrayOutputStream outstream = new ByteArrayOutputStream();
                mybitmap.compress(Bitmap.CompressFormat.JPEG, 85, outstream);
                byte[] byteArray = outstream.toByteArray();

                stream.write(byteArray);
                stream.close();

                Toast.makeText(context, "Downloaded Schedule to SDCARD", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

        }

    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return 1;
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        //Context context = getContext().getApplicationContext();


        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

}