package com.example.viking.tsx6;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.example.viking.tsx6.Fragments.About_Fragment;
import com.example.viking.tsx6.Fragments.Config;
import com.example.viking.tsx6.Fragments.Contact_us_fragment;
import com.example.viking.tsx6.Fragments.Nethunt_Fragment;
import com.example.viking.tsx6.Fragments.Offline_fragment;
import com.example.viking.tsx6.Fragments.Online_fragment;
import com.example.viking.tsx6.Fragments.Sponsors;
import com.example.viking.tsx6.Fragments.login;
import com.example.viking.tsx6.Fragments.logout;
import com.example.viking.tsx6.Fragments.register;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private MaterialViewPager mViewPager;
    PagerSlidingTabStrip pagerSlidingTabStrip;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private static Context context;
    private EditText user, pass;
    public static String  Password, email;
    public static TextView firstname_textview;
    Dialog dialog;
    public String id = "v=_yXcoACi0kE";
    public static Typeface typeface, typeface_1, typeface_2, typeface_3,typeface_4,typeface_5;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences notification_sharedpreferences;
    PendingIntent pendingIntent;


    public static FloatingActionButton fab;
    Boolean check_login;
    String sharedprefrences_username, sharedprefrences_password;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        context = this.getApplicationContext();

        //applying fonts
        typeface = Typeface.createFromAsset(getAssets(), "fonts/Bangers.ttf");
        typeface_1 = Typeface.createFromAsset(getAssets(), "fonts/ChallengeContour.ttf");
        typeface_2 = Typeface.createFromAsset(getAssets(), "fonts/PlayfairDisplay.ttf");
        typeface_3 = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");
        typeface_4 = Typeface.createFromAsset(getAssets(), "fonts/KGBlankSpaceSketch.ttf");
        typeface_5 = Typeface.createFromAsset(getAssets(), "fonts/KGBrokenVesselsSketch.ttf");

        firstname_textview = (TextView) findViewById(R.id.username);
        firstname_textview.setTypeface(typeface_2);


        //to save email and password
        sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        check_login = sharedPreferences.getBoolean("key1", false);
        sharedprefrences_username = sharedPreferences.getString("Username", "");
        sharedprefrences_password =sharedPreferences.getString("Password","");


        //to get notification
        notification_sharedpreferences=getSharedPreferences("Notification",MODE_PRIVATE);
        boolean notification[] = new boolean[13];
        for(int i=0;i<13;i++)
        {
            notification[i]=notification_sharedpreferences.getBoolean("k"+(i+1),true);
        }
        int min=21,hour=12,date=14;
        for(int i=0;i<13;i++)
        {
            if(i==2)
            {
                date++;
                hour=9;
                min=15;
            }
            if(i==7)
            {
                //date++;
                hour=13;
                min=2;
            }
            if (notification[i]) {
                notify(date, hour, min);
                ed = notification_sharedpreferences.edit();
                ed.putBoolean("k" + (i + 1), false);
                ed.commit();
            }
            min+=2;
        }


        //to print first name of logined user
        TextView tv = (TextView) findViewById(R.id.logo_white);
        tv.setTypeface(typeface);


        //material view pager and tab layout
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        pagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.materialviewpager_pagerTitleStrip);
        toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setDisplayShowHomeEnabled(false);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(false);
            }
        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 6) {
                    case 0:
                        return About_Fragment.newInstance(MainActivity.this);
                    case 1:
                        return Online_fragment.newInstance();
                    case 2:
                        return Offline_fragment.newInstance();
                    case 3:
                        return Nethunt_Fragment.newInstance();
                    case 4:
                        return Sponsors.newInstance();
                    case 5:
                        return Contact_us_fragment.newInstance();
                    default:
                        return Online_fragment.newInstance();
                }

            }

            @Override
            public int getCount() {

                return 6;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 6) {
                    case 0:
                        return "About";
                    case 1:
                        return "ONLINE";
                    case 2:
                        return "OFFLINE";
                    case 3:
                        return "NET HUNT";
                    case 4:
                        return "SPONSORS";
                    case 5:
                        return "CONTACT US";

                }
                return "";
            }
        });
        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
//                switch (page) {
//                    case 0:
//
//                        return HeaderDesign.fromColorAndDrawable(
//                                Color.parseColor("#FF5722"),ContextCompat.getDrawable(getApplicationContext(), R.drawable.colarge));
//                    case 1:
//                        return HeaderDesign.fromColorAndDrawable(
//                                Color.parseColor("#FF5722"), ContextCompat.getDrawable(getApplicationContext(), R.drawable.colarge));
//                    case 2:
//                        return HeaderDesign.fromColorAndDrawable(
//                                Color.parseColor("#FF5722"), ContextCompat.getDrawable(getApplicationContext(), R.drawable.colarge));
//                    case 3:
//                        return HeaderDesign.fromColorAndDrawable(
//                                Color.parseColor("#FF5722"), ContextCompat.getDrawable(getApplicationContext(), R.drawable.splash_back));
//                    case 4:
//                        return HeaderDesign.fromColorAndDrawable(
//                                Color.parseColor("#FF5722"), ContextCompat.getDrawable(getApplicationContext(), R.drawable.splash_back));
//                    case 5:
//                        return HeaderDesign.fromColorAndDrawable(
//                                Color.parseColor("#FF5722"), ContextCompat.getDrawable(getApplicationContext(), R.drawable.splash_back));

//                }

                return HeaderDesign.fromColorAndDrawable(
                        Color.parseColor("#FF5722"), ContextCompat.getDrawable(getApplicationContext(), R.drawable.colarge));
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        mViewPager.getViewPager().setCurrentItem(1);


        //Floating Action Buttons
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(Color
                .parseColor("#03A9F4")));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //logout
                if (Config.USERNAME!=null) {
                    dialog = new Dialog(MainActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.logout);
                    dialog.show();

                }
                //for login
                else {

                    dialog = new Dialog(MainActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.for_new_floating);
                    dialog.show();

                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void chrome(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("www.cadnitd.co.in")));
    }

    public void mail(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("technoshine.ca@gmail.com");
        startActivity(emailIntent);
    }

    public void youtube(View view) {
        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=_yXcoACi0kE"));
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=3QBamv6Dm8A"));
        startActivity(intent);
    }

    //to call server for registraion
    public void register_button(View view) {
        register register = new register();
        register.newInstance(dialog, MainActivity.this);

    }

    //to call server for login
    public void login_button(View view) {
        user = (EditText) dialog.findViewById(R.id.editText_username);
        pass = (EditText) dialog.findViewById(R.id.editText_password);
        email = user.getText().toString();
        Password = pass.getText().toString();
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(Password))
        {
            Config.showToast(context,"Required field(s) missing");
        }
        else
        {
            login login = new login();
            login.newInstance(email, Password, MainActivity.this, dialog);
        }

    }

    //to set firstname on activity main after login
    public static void setname(String firstname) {


            firstname_textview.setText("Hello, " + firstname);
            fab.setImageResource(R.mipmap.ic_lock_open_white);
            SharedPreferences.Editor ed = sharedPreferences.edit();
            ed.putBoolean("key1", true);
            ed.putString("Username", email);
            ed.putString("Password", Password);
            ed.commit();





    }

    //to remove firstname from activity after logout
    public static void removename(){
        firstname_textview.setText("");
        fab.setImageResource(R.mipmap.fingerprint_icon);

    }

    //to open login dialog
    public void login_dialog(View view) {
        dialog.dismiss();
        //sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        check_login = sharedPreferences.getBoolean("key1", false);
        sharedprefrences_username = sharedPreferences.getString("Username", "");
        sharedprefrences_password =sharedPreferences.getString("Password","");
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.log_in);
        dialog.show();
        user = (EditText) dialog.findViewById(R.id.editText_username);
        pass = (EditText) dialog.findViewById(R.id.editText_password);
        if (check_login) {
            user.setText(sharedprefrences_username);
            pass.setText(sharedprefrences_password);
            //firstname_textview.setText("Hello, " + sharedprefrences_username);
        }


    }

    //to call server for logout
    public void logout(View view) {

        logout logout = new logout();
        logout.newInstance(MainActivity.this, dialog);



    }

    public void cancel(View view) {
        dialog.dismiss();
    }

    //to call register dialog
    public void register_dialog(View view) {
        dialog.dismiss();
        dialog = new Dialog(MainActivity.this,R.style.ThemeDialogCustom);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.register);
        // dialog.getWindow().setFormat(PixelFormat.TRANSLUCENT);
        dialog.setTitle("register");
        dialog.show();

    }

    //to start nethunt
    public void start_nethunt(View view) {

        if (Config.USERNAME == null) {
            Toast.makeText(getApplication(), "You have to login first", Toast.LENGTH_SHORT).show();
            dialog = new Dialog(MainActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.for_new_floating);
            dialog.show();
        } else {
            Intent intent = new Intent(this, Net_hunt.class);
            startActivity(intent);
        }

    }

    public void backspace(View view) {
        dialog.dismiss();
    }


    public void schedule(View view)
    {
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.schedule);
        dialog.show();
    }
    void notify(int date,int hour,int min)
    {

        int d=date,h=hour,mn=min;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, d);
        calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
        calendar.set(Calendar.YEAR, 2016);

        calendar.set(Calendar.HOUR_OF_DAY, h);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, 0);

        final int id=(int)System.currentTimeMillis();

        Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, id, myIntent, PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }

    @SuppressWarnings("NewApi")
    public static Bitmap blurRenderScript(Bitmap smallBitmap, int radius) {


        try {
            smallBitmap = RGB565toARGB888(smallBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Bitmap bitmap = Bitmap.createBitmap(
                smallBitmap.getWidth(), smallBitmap.getHeight(),
                Bitmap.Config.ARGB_8888);

        RenderScript renderScript = RenderScript.create(context);

        Allocation blurInput = Allocation.createFromBitmap(renderScript, smallBitmap);
        Allocation blurOutput = Allocation.createFromBitmap(renderScript, bitmap);

        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(renderScript,
                Element.U8_4(renderScript));
        blur.setInput(blurInput);
        blur.setRadius(radius); // radius must be 0 < r <= 25
        blur.forEach(blurOutput);
        blurOutput.copyTo(bitmap);
        renderScript.destroy();

        return bitmap;

    }

    private static Bitmap RGB565toARGB888(Bitmap img) throws Exception {
        int numPixels = img.getWidth() * img.getHeight();
        int[] pixels = new int[numPixels];

        //Get JPEG pixels.  Each int is the color values for one pixel.
        img.getPixels(pixels, 0, img.getWidth(), 0, 0, img.getWidth(), img.getHeight());

        //Create a Bitmap of the appropriate format.
        Bitmap result = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.ARGB_8888);

        //Set RGB pixels.
        result.setPixels(pixels, 0, result.getWidth(), 0, 0, result.getWidth(), result.getHeight());
        return result;
    }

}
