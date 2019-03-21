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
    // It is called when the database version is detected
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE + "'");
        onCreate(db);
    }

    /**
     * Inserts values in the table
     * @param user
     * @return
     */
    public long create(Users user) {
        // If insert data in  the database, use getWritableDatabase()
        SQLiteDatabase db = this.getWritableDatabase();
        // Protects the values
        ContentValues values = new ContentValues();
        values.put(MAIL, user.getMail());
        values.put(PASSWORD, user.getPassword());
        // Insert
        return db.insert(TABLE, null, values);
    }

    /**
     * Contains the user's list
     * @return
     */
    public ArrayList<Users> getList() {

        // Assign a SQL query
        String selectQuery = "SELECT  * FROM " + TABLE;
        // If read data in the database, use getReadableDatabase
        SQLiteDatabase db = this.getReadableDatabase();
        // Cursor allows to read the records in the database
        // rawQuery() agrees to execute the request
       /* Cursor cursor = db.query(TABLE, new String[] { ID, MAIL,
                PASSWORD }, null, null, null, null, MAIL);*/
        Cursor cursor = db.rawQuery(selectQuery, null);
        // If it can read the first line
        ArrayList<Users> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            // In {do} I get the value of the first line (corresponding to moveToFirst());
            // Create list

            do {
                // I create a new user
                Users user = new Users();
                // I get the values back => (get ... back : récupère)
                user.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                user.setMail(cursor.getString(cursor.getColumnIndex(MAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
                // and adding to list
                list.add(user);
                // it starts the process again (il recommence le processus)
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public Integer deleteUser(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE, ID + "= " + id, null);
    }
}
