package com.example.utsrezzaagustin.biodata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String database_name = "DB_Appank";
    public static final String table_name = "biodata";
    public static final String clm_id = "_id";
    public static final String clm_nim = "Nim";
    public static final String clm_namalkp = "Nama";
    public static final String clm_nohp = "Nohp";
    public static final String clm_alamat = "Alamat";
    public static final String clm_jk = "JK";
    public static final String clm_status = "status";
    public static final String clm_jurusan = "jurusan";
    public static final String clm_angkatan = "angkatan";

    private SQLiteDatabase db;

    public DataBaseHelper(Context context) {
        super(context, database_name, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name + "(" + clm_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + clm_nim + " TEXT, " + clm_namalkp + " TEXT, " + clm_nohp + " TEXT, "
                + clm_alamat + " TEXT, " + clm_jk + " TEXT, " + clm_status + " TEXT, " + clm_jurusan +" TEXT," + clm_angkatan +" TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int x) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
    }

    //Get All SQLite Data
    public Cursor allData(){
        Cursor cur = db.rawQuery("SELECT * FROM " + table_name , null);
        return cur;
    }

    //Get 1 Data By ID
    public Cursor oneData(Long id){
        Cursor cur = db.rawQuery("SELECT * FROM " + table_name + " WHERE " + clm_id + "=" + id, null);
        return cur;
    }

    //Insert Data to Database
    public void insertData(ContentValues values){
        db.insert(table_name, null, values);
    }

    //Update Data
    public void updateData(ContentValues values, long id){
        db.update(table_name, values, clm_id + "=" + id, null);
    }

    //Delete Data
    public void deleteData(long id){
        db.delete(table_name, clm_id + "=" + id, null);
    }
}
