package com.example.viking.tsx6;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;

/**
 * Created by viking on 12/9/16.
 */
public class MyReceiver extends BroadcastReceiver
{
    public static String title,msg;
    public static int id, flag;
    public static NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("notf_flag", Context.MODE_PRIVATE);
        flag =sharedPreferences.getInt("k1",1);


        DatabaseHelper mdb = new DatabaseHelper(context);
        SQLiteDatabase db = mdb.getWritableDatabase();
        String qry1 = "select * from " + DatabaseHelper.TABLENAME +" where "+DatabaseHelper.ID+"="+ flag;
        Cursor c = db.rawQuery(qry1, null);
        c.moveToFirst();
        id = c.getInt(0);
        title = c.getString(1);
        msg = c.getString(2);


        notificationManager = (NotificationManager) context.getApplicationContext().getSystemService(context.getApplicationContext().NOTIFICATION_SERVICE);
        Intent i = new Intent(context,Splash_screen.class);
        int ids = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, ids, i, PendingIntent.FLAG_ONE_SHOT);
        Notification no = new Notification.Builder(context.getApplicationContext())
                .setSmallIcon(R.mipmap.logo)
                .setContentTitle(title)
                .setContentText(msg)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .build();

        notificationManager.notify(id, no);
        Vibrator vb=(Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        vb.vibrate(1000);

        flag = flag +1;
        SharedPreferences.Editor ed=sharedPreferences.edit();
        ed.putInt("k1", flag);
        ed.commit();
    }
}