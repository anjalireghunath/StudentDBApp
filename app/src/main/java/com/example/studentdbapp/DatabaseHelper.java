package com.example.studentdbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String DbName="College.db";
    static String TableName="students";
    static String Col1="id";
    static String Col2="Name";
    static String Col3="RollNo";
    static String Col4="AdmNo";
    static String Col5="College";
    public DatabaseHelper(Context context) {
        super(context,DbName ,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table "+TableName+
                "("+Col1+" integer primary key autoincrement,"+
                Col2+" text,"+
                Col3+" text,"+
                Col4+" text,"+
                Col5+" text)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insertData(String name,String rollno,String admno,String college){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put(Col2,name);
        content.put(Col3,rollno);
        content.put(Col4,admno);
        content.put(Col5,college);

        long status=db.insert(TableName,null,content);
        if(status==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor searchData(String AdmNo){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="select * from "+TableName+" where "+Col4+"='"+AdmNo+"'";
        Cursor c=db.rawQuery(query,null);
        return c;
    }



}
