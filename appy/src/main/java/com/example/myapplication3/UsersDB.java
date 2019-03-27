package com.example.myapplication3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UsersDB {


    // Table name
    private static final String TABLE = "user";
    // name column
    private static final String ID = "id";
    private static final String MAIL = "mail";
    private static final String PASSWORD = "password";
    // Database connection
    private SQLite sqlite;


    public UsersDB(Context context){
        sqlite = new SQLite(context);
    }


    /**
     * Inserts values in the table
     * @param user
     * @return
     */
    public long create(Users user) {
        // If insert data in  the database, use getWritableDatabase()
        SQLiteDatabase db = sqlite.getWritableDatabase();
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
        SQLiteDatabase db = sqlite.getReadableDatabase();
        // Cursor allows to read the records in the database
        // rawQuery() agrees to execute the request
        Cursor cursor = db.rawQuery(selectQuery, null);
        // If it can read the first line
        ArrayList<Users> list = new ArrayList<>();
        // Reading the first line
        if (cursor.moveToFirst()) {
            // In {do} I get the value of the first line (corresponding to moveToFirst())
            do {
                // I create a new user
                Users user = new Users();
                // I get the values back => (get ... back : récupère)
                user.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                // getColumnIndex(Column name) : Find the identifier of the column corresponding to the name
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

    /**
     * Delete a user
     * @param id
     * @return
     */
    public Integer deleteUser(int id){
        SQLiteDatabase db = sqlite.getWritableDatabase();
        return db.delete(TABLE, ID + "= " + id, null);
    }

}
