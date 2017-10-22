package com.example.viking.tsx6;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by viking on 31/7/16.
 */
public class Splash_screen extends Activity implements Animation.AnimationListener {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;
    Animation animation,ani;
    TextView textView;
    ImageView imageView_text;
    Context context;
    public static Typeface typeface,typeface_1,typeface_2,typeface_3;
    int flag=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        typeface=Typeface.createFromAsset(getAssets(),"fonts/Bangers.ttf");
        typeface_1=Typeface.createFromAsset(getAssets(),"fonts/ChallengeContour.ttf");
        typeface_2=Typeface.createFromAsset(getAssets(),"fonts/PlayfairDisplay.ttf");
        typeface_3=Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");
        imageView_text = (ImageView)findViewById(R.id.splash_text);

        ImageView imageView = (ImageView) findViewById(R.id.splash_image);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(Splash_screen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);


        animation= AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce);
        ani= AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_in);

        // set animation listener
        animation.setAnimationListener(this);
        ani.setAnimationListener(this);
        imageView.startAnimation(animation);
    }
    @Override
    public void onAnimationEnd(Animation anim) {

        // Take any action after completing the animation
        if(flag==1)
        {
            imageView_text.setVisibility(View.VISIBLE);
            imageView_text.startAnimation(ani);
            flag=0;
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

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
