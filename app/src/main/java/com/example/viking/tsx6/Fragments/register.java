package com.example.viking.tsx6.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.viking.tsx6.MainActivity;
import com.example.viking.tsx6.R;
import com.example.viking.tsx6.for_login;
import com.example.viking.tsx6.for_register;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by viking on 19/8/16.
 */
public class register extends Fragment {
    Dialog dialog_register;
    Context context;

    EditText fname,lname,clg,cntry,cnt,mail,pass,ver_pass;
    String first_name,last_name,college,country,contact,email,password,verify_password;

    public void newInstance(Dialog dialog,Context context)
    {
        this.context = context;
        fname = (EditText) dialog.findViewById(R.id.fname);
        lname = (EditText) dialog.findViewById(R.id.lname);
        clg = (EditText) dialog.findViewById(R.id.college);
        cnt = (EditText) dialog.findViewById(R.id.contact);
        cntry = (EditText) dialog.findViewById(R.id.country);
        mail = (EditText) dialog.findViewById(R.id.email);
        pass = (EditText) dialog.findViewById(R.id.pswd);
        ver_pass = (EditText) dialog.findViewById(R.id.vfypswd);

        first_name = fname.getText().toString();
        last_name = lname.getText().toString();
        college = clg.getText().toString();
        country = cntry.getText().toString();
        contact = cnt.getText().toString();
        email = mail.getText().toString();
        password = pass.getText().toString();
        verify_password = ver_pass.getText().toString();
        System.out.println(first_name+".."+last_name+".."+college+".."+country+"..");

        if(TextUtils.isEmpty(first_name)||TextUtils.isEmpty(last_name)||TextUtils.isEmpty(college)||TextUtils.isEmpty(country)
                ||TextUtils.isEmpty(contact)||TextUtils.isEmpty(email)||TextUtils.isEmpty(password)||TextUtils.isEmpty(verify_password)){
            System.out.println("Required field(s) missing 1");
           Toast.makeText(context, "Required field(s) missing", Toast.LENGTH_SHORT).show();
        }
        else if(contact.length()!=10){
            System.out.println("Required field(s) missing 5 "+password+"..."+verify_password);
            Toast.makeText(context, "Phone number is not valid!!"+contact.length()+contact, Toast.LENGTH_SHORT).show();
        }
        else if (!isValidPassword(password)) {
            System.out.println("Required field(s) missing 2");
            Toast.makeText(context, "Password Invalid!! Please enter an alphanumeric password with a minimum of six characters", Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(verify_password)){
            System.out.println("Required field(s) missing 3 "+password+"..."+verify_password);
            Toast.makeText(context, "Passwords do not match. Try again!!", Toast.LENGTH_SHORT).show();
        }
        else  if (!isValidEmail(email)) {
            System.out.println("Required field(s) missing 4");
            Toast.makeText(context, "Invalid email", Toast.LENGTH_SHORT).show();
        }else{
            new on_register().execute();
        }

        dialog_register = dialog;
    }


    private class on_register extends AsyncTask<Void, Void, String>
    {

         private final ProgressDialog dialog = new ProgressDialog(context);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

                 dialog.setMessage("Uploading, please wait.......");
                 dialog.show();
        }
        @Override
        protected String doInBackground(Void... params)
        {

            for_register for_register = new for_register("","","","","","","");
            String line, response = "";
            String POST_PARAMS = "firstname=" +first_name ;
            POST_PARAMS += "&lastname=" +last_name;
            POST_PARAMS += "&college=" +college;
            POST_PARAMS += "&country=" +country;
            POST_PARAMS += "&contact=" +contact;
            POST_PARAMS += "&email=" +email;
            POST_PARAMS += "&password=" +password;
            System.out.println("in the register class.........................1");
//            POST_PARAMS += "&VerifyPassword=" +verify_password;
            if(haveNetworkConnection())
            {
                try {

//                    URL url = new URL("http://192.168.0.102/login/register.php");
                    URL url = new URL(Config.REGISTER_URL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    System.out.println("in the register class.........................2");
                    if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
                        //conn.setRequestProperty("Connection", "close");
                    }
                    //conn.setRequestProperty("Accept-Encoding", "");
                    conn.setReadTimeout(15000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    System.out.println("in the register class.........................3");

                    // For POST only - BEGIN
                    conn.setDoOutput(true);
                    OutputStream os = conn.getOutputStream();
                    os.write(POST_PARAMS.getBytes());
                    os.flush();
                    os.close();
                    // For POST only - END
                    //conn.connect();
                    System.out.println("in the register class.........................4");

                    int responseCode = conn.getResponseCode();

                    if (responseCode == HttpsURLConnection.HTTP_OK) {
                    // read the response
                        System.out.println("Response Code: " + conn.getResponseCode());
                        //InputStream in = new BufferedInputStream(conn.getInputStream());

                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        while ((line = br.readLine()) != null) {
                            response += line;
                        }
                        Log.e("RESPONSE", response);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
            {
                response = "no internet";
            }

            return response;
        }
        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            dialog.dismiss();
            if(response.equals("no internet"))
            {
                Toast.makeText(context, "No internet Connection", Toast.LENGTH_SHORT).show();
            }
            else
            {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    Boolean success=jsonObject.getBoolean("success");
                    String message=jsonObject.getString("message");
                    Config.showToast(context,message);
                    if(success==true)
                    {
                        dialog_register.dismiss();

                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Config.showToast(context, "Can't connect to server, please try again later or close the proxy IP if it is working ");

                }
            }





        }
    }
    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
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
