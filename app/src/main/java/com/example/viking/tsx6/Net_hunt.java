package com.example.viking.tsx6;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viking.tsx6.Fragments.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by viking on 3/9/16.
 */
public class Net_hunt extends AppCompatActivity {

    String question,answer,img;
    int level;
    TextView txt_question,txt_level,nethunt_title,nethunt_level,nethunt_query;
    EditText txt_answer;
    ImageView img_ques;
    Context context;


    Bitmap bitmap;
    ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_hunt);
        Intent intent = getIntent();
        String a = intent.getStringExtra("username");
        context = this.getApplicationContext();

        //to set font
        txt_question=(TextView)findViewById(R.id.ques);
        txt_level=(TextView)findViewById(R.id.level);
        txt_answer=(EditText)findViewById(R.id.et1);
        nethunt_title=(TextView)findViewById(R.id.nethunt_title);
        nethunt_level=(TextView)findViewById(R.id.level);
        nethunt_query=(TextView)findViewById(R.id.query);
        nethunt_title.setTypeface(MainActivity.typeface_5);
        nethunt_query.setTypeface(MainActivity.typeface_5);
        new on_start().execute();

    }
    //to go back to main activity
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    //to varify the answer
    public void verify(View v)
    {
        txt_answer=(EditText)findViewById(R.id.et1);
        answer=txt_answer.getText().toString();
        answer=answer.trim();
        txt_answer.setText(answer);
        new to_verify().execute();
    }
    //to get level and question
    public void play(View view)
    {
        setContentView(R.layout.net_hunt);
        new on_start().execute();
    }

    private class on_start extends AsyncTask<Void, Void, for_question>
    {
        private final ProgressDialog dialog = new ProgressDialog(Net_hunt.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Retrieving Question.......");
            dialog.show();
        }
        @Override
        protected for_question doInBackground(Void... params)
        {
            for_question forQuestion = new for_question("","","","");
            String line, response = "";
            String POST_PARAMS = "email=" + Config.USERNAME ;
            POST_PARAMS += "&password=" +Config.PASSWORD;
            POST_PARAMS += "&sess_id=" +Config.SESS_ID;


                try {

                    URL url = new URL(Config.GET_LEVEL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {

                    }

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
// read the response
                        System.out.println("Response Code: " + conn.getResponseCode());
                        //InputStream in = new BufferedInputStream(conn.getInputStream());

                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        while ((line = br.readLine()) != null) {
                            response += line;
                        }
                        Log.e("RESPONSE", response);

                        JSONObject obj = new JSONObject(response);
                        forQuestion=convert_question(obj);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Config.showToast(context, "Can't connect to server..try again later");
                    onBackPressed();
                }




            return forQuestion;
        }
        @Override
        protected void onPostExecute(for_question result) {
            super.onPostExecute(result);
            dialog.dismiss();
            try
            {
                if(result.level.equals("-2"))
                {
                    Config.showToast(context, "NetHunt isn't started yet...");
                    onBackPressed();

                }else if(result.level.equals("-1"))
                {
                    img_ques=(ImageView)findViewById(R.id.img_view);
                    img_ques.setVisibility(View.INVISIBLE);
                    txt_question=(TextView)findViewById(R.id.ques);
                    txt_answer=(EditText)findViewById(R.id.et1);
                    txt_question.setText("You did it!!!");
                    txt_answer.setVisibility(View.INVISIBLE);
                    txt_level=(TextView)findViewById(R.id.level);
                    txt_level.setText("Level Completed");
                    Button button = (Button)findViewById(R.id.btn);
                    button.setVisibility(View.INVISIBLE);

                }
                else if(result.img.equals("image"))
                {
                    img_ques=(ImageView)findViewById(R.id.img_view);
                    Log.e("RESPONSE", "it contain image");
                    img_ques.setVisibility(View.VISIBLE);
                    txt_question=(TextView)findViewById(R.id.ques);
//                new LoadImage().execute("http://3.bp.blogspot.com/_8IIY6dw5JrA/Stx42qhuZdI/AAAAAAAABTs/ZtulMz3IBXM/s400/AMAIZING%252BNATURAL%252BWALLPAPER%252B1.jpg");
                    new LoadImage().execute(result.question);
                    set_img(result.level,result.answer);

                }
                else
                {
                    set_ques(result.question, result.level, result.answer);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                Config.showToast(context, "Can't connect to server..try again later");
                onBackPressed();
            }



        }
        private for_question convert_question(JSONObject obj) throws JSONException {

            String ques = obj.getString("question");
            String ans = null;
            Config.SESS_ID=obj.getString("sess_id");
            String lev = obj.getString("level");
            String img = obj.getString("type");
            return new for_question(ques, ans, lev,img);
        }
    }


    void set_img(String l,String ans)
    {
        img_ques=(ImageView)findViewById(R.id.img_view);
        txt_question=(TextView)findViewById(R.id.ques);
        txt_level=(TextView)findViewById(R.id.level);
        txt_question.setVisibility(View.INVISIBLE);
        txt_level.setText("Level "+l);
        level=Integer.parseInt(l);
        answer=ans;
        txt_answer=(EditText)findViewById(R.id.et1);
        txt_answer.setText("");
    }
    void set_ques(String q,String l,String ans)
    {
        txt_question=(TextView)findViewById(R.id.ques);
        txt_level=(TextView)findViewById(R.id.level);
        img_ques=(ImageView)findViewById(R.id.img_view);
        txt_question.setText(q);
        txt_question.setVisibility(View.VISIBLE);
        img_ques.setVisibility(View.INVISIBLE);
        txt_level.setText("Level "+l);
        level=Integer.parseInt(l);
        question=txt_question.getText().toString();
        answer=ans;
        txt_answer=(EditText)findViewById(R.id.et1);
        txt_answer.setText("");
    }
    class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Net_hunt.this);
            pDialog.setMessage("Loading Image ....");
            pDialog.show();

        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if(image != null){
                img_ques.setImageBitmap(image);
                pDialog.dismiss();

            }else{

                pDialog.dismiss();
                Toast.makeText(Net_hunt.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

            }
        }
    }
    private class to_verify extends AsyncTask<Void, Void, Boolean> {
        private final ProgressDialog dialog = new ProgressDialog(Net_hunt.this);
        Boolean success;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Retrieving Question.......");
            dialog.show();
        }
        @Override
        protected Boolean doInBackground(Void... params) {
            for_question forQuestion = new for_question("","", "", "");
            String line, response = "";
            String POST_PARAMS = "level=" + level;
            POST_PARAMS += "&answer=" + answer;
            POST_PARAMS +="&username="+ Config.USERNAME;
            POST_PARAMS +="&password="+ Config.PASSWORD;
            POST_PARAMS +="&sess_id="+ Config.SESS_ID;

            try {

                URL url = new URL(Config.CHECK);
                System.out.println(Config.CHECK);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
                    //conn.setRequestProperty("Connection", "close");
                }
                //conn.setRequestProperty("Accept-Encoding", "");
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
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
// read the response
                    System.out.println("Response Code: " + conn.getResponseCode());
                    //InputStream in = new BufferedInputStream(conn.getInputStream());

                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                    Log.e("RESPONSE", response);




                    JSONObject obj = new JSONObject(response);
                    //forQuestion=convert_response(obj);
                    success = obj.getBoolean("success");
                    Config.SESS_ID=obj.getString("sess_id");

                }
                //System.out.println(response);
            } catch (Exception e) {
                e.printStackTrace();
                Config.showToast(context, "Can't connect to server..try again later");
                //onBackPressed();
            }

            return success;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            dialog.dismiss();

            try
            {
                if(result==true)
                {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
                else
                {
                    Log.e("RESPONSE","Wrong answer");
                }

            }catch (Exception e) {
                e.printStackTrace();
                Config.showToast(context, "Can't connect to server..try again later");
                //onBackPressed();
            }

        }
    }

}
