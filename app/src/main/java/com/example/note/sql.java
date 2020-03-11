package com.example.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class sql extends SQLiteOpenHelper {


    public static final String Database_Name = "my_note.db";
    public static final int Datebase_Version = 1;
    public static final String TABLE_NAME = "NOTE";

    public sql(Context context){
        super(context,Database_Name,null,Datebase_Version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String x ="CREATE TABLE "+TABLE_NAME+"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "title varchar ," +
                "note varchar " +
                ");";
        db.execSQL(x);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME );
        onCreate(db);

    }

    public ArrayList<Note> read_data(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c =  db.rawQuery("SELECT * FROM "+TABLE_NAME ,null);
        ArrayList<Note>gom3a =new ArrayList<>();
        while (c.moveToNext()){
            int id = c.getInt(c.getColumnIndex("id"));
            String title = c.getString(c.getColumnIndex("title"));
            String note = c.getString(c.getColumnIndex("note"));
            Note mynote = new Note(id,title,note);
            gom3a.add(mynote);
        }
        return gom3a;
    }

    public void write_data(String t,String N){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues h = new ContentValues();
        h.put("title",t);
        h.put("note",N);
        db.insert(TABLE_NAME,null,h);
    }

    public void update_data(String nt ,int id ,String nn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues h = new ContentValues();
        h.put("title",nt);
        h.put("note",nn);
        db.update(TABLE_NAME,h,"id=?",new String[]{String.valueOf(id)});
    }

    public void delete_data(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"id=?",new String[]{String.valueOf(id)});
    }
}
