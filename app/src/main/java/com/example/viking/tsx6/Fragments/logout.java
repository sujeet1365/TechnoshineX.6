package com.example.viking.tsx6.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.viking.tsx6.MainActivity;
import com.example.viking.tsx6.for_login;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by viking on 7/9/16.
 */
public class logout extends Fragment {


    Context context;
    Dialog dialog;

    public void newInstance(Context context,Dialog dialog) {

        this.context = context;
        this.dialog = dialog;

        new on_start().execute();
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }
    private class on_start extends AsyncTask<Void, Void, String>
    {
         private final ProgressDialog dialog_1 = new ProgressDialog(context);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
                dialog_1.setMessage("Loading, please wait.......");
                 dialog_1.show();
        }
        @Override
        protected String doInBackground(Void... params)
        {
            for_login for_login = new for_login("","");
            String line, response = "";
            String POST_PARAMS = "sess_id=" +Config.SESS_ID ;

            Log.e("POST PARAMS", POST_PARAMS);

            if(haveNetworkConnection())
            {
                try {

                    Log.e("aasfasf","asfasf");

//                    URL url = new URL("http://192.168.0.102/login/login.php");
                    URL url = new URL(Config.LOGOUT_URL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
                        //conn.setRequestProperty("Connection", "close");
                    }
                    //conn.setRequestProperty("Accept-Encoding", "");
                    conn.setReadTimeout(30000);
                    conn.setConnectTimeout(30000);
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);

                    // For POST only - BEGIN
                    conn.setDoOutput(true);
                    OutputStream os = conn.getOutputStream();
                    os.write(POST_PARAMS.getBytes());
                    os.flush();
                    os.close();
                    // For POST only - END
                    //conn.connect();

                    int responseCode = conn.getResponseCode();

                    if (responseCode == HttpsURLConnection.HTTP_OK) {
                        Log.e("aasfasf","bbbbbbbbbbbbbb");
// read the response
                        System.out.println("Response Code: " + conn.getResponseCode());
                        //InputStream in = new BufferedInputStream(conn.getInputStream());

                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        while ((line = br.readLine()) != null) {
                            response += line;
                        }
                    }
                    else
                    {
                        response = "server";
                    }

                    Log.e("aasfasf","aaaaaaaaaaaa");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                response = "no internet";
            }

            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            Log.e("RESPONSE", response);
            dialog_1.dismiss();
            if(response.equals("no internet"))
            {
                Toast.makeText(context, "No internet Connection", Toast.LENGTH_SHORT).show();
            }
            else if (response.equals("server"))
            {
                Toast.makeText(context, "Can't connect to server", Toast.LENGTH_SHORT).show();
            }
            else
            {


                try {
                    JSONObject jsonObject=new JSONObject(response);
                    boolean success=jsonObject.getBoolean("success");
                    String message=jsonObject.getString("message");
                    if(success) {
                        //Config.SESS_ID=jsonObject.getString("sess_id");
                        Config.showToast(context, message);
                        Config.USERNAME=null;
                        Config.LOGGED_IN=false;
                        Config.SESS_ID=null;
                        Config.PASSWORD=null;
                        dialog.dismiss();
                        MainActivity.removename();
                    }
                    else {
                        Config.showToast(context, message);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Config.showToast(context, "Can't connect to server, please try again later or close the proxy IP if it is working ");

                }
            }

        }
    }


    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
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
