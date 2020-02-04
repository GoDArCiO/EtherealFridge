package com.example.EtherealFridge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 2/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "food_table19";
    private static final String TABLE2_NAME = "stats_table19";
    private static final String TABLE3_NAME = "history_table19";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    //int
    private static final String COL3= "cena_name";
    private static final String COL4= "ilosc_kcal";
    private static final String COL5= "ilosc_bialko";
    private static final String COL6= "ilosc_tluszcz";
    //data
    private static final String COL7= "data";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT," + COL3 +" int," + COL4 +" int," + COL5 +" int," + COL6 +" int)";
        db.execSQL(createTable);
        String createStatTable = "CREATE TABLE " + TABLE2_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT," + COL3 +" int," + COL4 +" int," + COL5 +" int," + COL6 +" int,"+ COL7+" TEXT)";
        db.execSQL(createStatTable);
        String createHistoryTable = "CREATE TABLE " + TABLE3_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT," + COL3 +" int," + COL4 +" int," + COL5 +" int," + COL6 +" int)";
        db.execSQL(createHistoryTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        onCreate(db);
    }


    public boolean addData(String item, int cena, int kcal, int bialko, int tluszcz) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        contentValues.put(COL3, cena);
        contentValues.put(COL4, kcal);
        contentValues.put(COL5, bialko);
        contentValues.put(COL6, tluszcz);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addStats(String item, int cena, int kcal, int bialko, int tluszcz, String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        contentValues.put(COL3, cena);
        contentValues.put(COL4, kcal);
        contentValues.put(COL5, bialko);
        contentValues.put(COL6, tluszcz);
        contentValues.put(COL7, data);

        long result = db.insert(TABLE2_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addHistory(String item, int cena, int kcal, int bialko, int tluszcz) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        contentValues.put(COL3, cena);
        contentValues.put(COL4, kcal);
        contentValues.put(COL5, bialko);
        contentValues.put(COL6, tluszcz);
        Integer uni=0;
        long result=0;
        String query = "SELECT count(*) FROM " + TABLE3_NAME+" WHERE "+COL2+" == "+"'"+item+"'"+";";//add to history only if uniq
        Cursor data = db.rawQuery(query, null);
        while(data.moveToNext()){
            uni = data.getInt(0);
        }
        if(uni==0) {
            result = db.insert(TABLE3_NAME, null, contentValues);
        }

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getHistory(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE3_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemCena(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL3 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemKcal(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL4 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemBialko(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL5 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemTluszcz(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL6 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor gethItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE3_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor gethItemCena(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL3 + " FROM " + TABLE3_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor gethItemKcal(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL4 + " FROM " + TABLE3_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor gethItemBialko(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL5 + " FROM " + TABLE3_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor gethItemTluszcz(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL6 + " FROM " + TABLE3_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getStatKcal(String dataS){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL4 + " FROM " + TABLE2_NAME +
                " WHERE " + COL7 + " = '" + dataS + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getStatBialko(String dataS){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL5 + " FROM " + TABLE2_NAME +
                " WHERE " + COL7 + " = '" + dataS + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getStatTluszcz(String dataS){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL6 + " FROM " + TABLE2_NAME +
                " WHERE " + COL7 + " = '" + dataS + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateName(String newName,int cena, int kcal, int bialko, int tluszcz, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET "
                + COL2 +
                " = '" + newName +"', "
                 + COL3 +
                " = '" + cena +"', "
                 + COL4 +
                " = '" + kcal +"', "
                 + COL5 +
                " = '" + bialko +"', "
                 + COL6 +
                " = '" + tluszcz +
                "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    public void clearStats(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE2_NAME + ";";
        db.execSQL(query);
    }

}