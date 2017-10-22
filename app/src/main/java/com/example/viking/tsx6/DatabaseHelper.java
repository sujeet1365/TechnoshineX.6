package com.example.viking.tsx6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by viking on 12/9/16.
 */
public class DatabaseHelper  extends SQLiteOpenHelper
{
    public static final String DBNAME="Notify";
    public static final int VERSION=1;
    public static final String TABLENAME="notification";
    public static final String ID="id";
    public static final String Title="title";
    public static final String Text="text";

    public DatabaseHelper(Context context)
    {
        super(context,DBNAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String qry="create table "+TABLENAME+" ("+ID+" INTEGER, "+Title+" TEXT, "+Text+" TEXT)";
        db.execSQL(qry);

//        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (1,'Ts X.6','Technoshine is now started')");
//        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (2,'JustClick','JustClick is getting started')");
//        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (3,'Nethunt','Nethunt is getting started')");
//        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (4,'Inspire India','Inspire India is getting started')");
//        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (5,'CodeWar','CodeWar is getting started')");
//        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (6,'Tattoo Making','Tatto MAking is getting started')");
//        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (7,'Brain Marathon','Brain Marathon is getting started')");
//        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (8,'CS','CounterStrike is getting started')");
//        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (9,'TresureHunt','Tresurehunt is getting started')");
//        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (10,'GadgetGuru','GadgetGuru is getting started')");
//        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (11,'POY','Performer Of the Year is getting started')");
//        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (12,'Trickology','Trickology is getting started')");
//        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (13,'TotalChaos','TotalChaos is getting started')");


        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (1,'Technoshine X.6','Finally the day has come!')");
        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (2,'Onlline Events Starting today!','Just Click Inspire India Nethunt is starting today Tap to open the app for more details')");


        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (3,'Code War','The countdown for the war begins in an hour')");
        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (4,'Net Hunt','Hurry Up! Last hour google ur answer Fast!')");
        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (5,'Counter Strike','Bundle up your gadgets its gonna start in an hour!')");
        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (6,'Code War','Hurry Up! One more hour left submit your code!')");
        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (7,'Trickology','#include<1 more hour>')");
        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (8,'Gadget Guru','Gizmo Freekz, the ultimate hour has come')");
        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (9,'Total Chaos','The Choas is about to start in an hour!')");
        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (10,'Treasure Hunt','The adrenaline rush starts in an hour')");
        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (11,'Performer Of the Year','Our main event is about to start')");
        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (12,'Brain Marathon','Ace Your Brain Cells and reach the venue')");
        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (13,'Inspire India','Hurry Up! One more hour left submit soon!')");
        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (14,'Just Click','Hurry Up! Last hour to increase your Likes!')");
        db.execSQL( "insert into " + DatabaseHelper.TABLENAME + " values (15,'Tattoo Making','One more hour left to reach D.M.Sen Audi')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}