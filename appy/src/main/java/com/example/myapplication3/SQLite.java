package com.example.myapplication3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class SQLite extends SQLiteOpenHelper  {

    // Database name
    private static final String DATABASE = "Users";
    // Database version
    private static final int VERSION = 2;
    // Table name
    private static final String TABLE = "user";
    // name column
    private static final String ID = "id";
    private static final String MAIL = "mail";
    private static final String PASSWORD = "password";

    // SQL query corresponding to the creation of the table
    private static final String CREATE_TABLE =
            "CREATE TABLE "
                    + TABLE
                    + "("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + MAIL + " TEXT" + ","
                    + PASSWORD + " TEXT"
                    + ");";

    // Context contains information on the status of the application
    public SQLite(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    // Create the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    // It is called when the database version is changed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE + "'");
        onCreate(db);
    }

}
