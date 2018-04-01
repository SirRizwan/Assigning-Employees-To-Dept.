package com.example.rizwan.sqllite;

/**
 * Created by Rizwan on 3/30/2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBClass extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "company.db";
    public static final int DATABASE_VERSION = 3;

    public static final String TABLE_NAME_EMP = "employee";

    public static final String COL_EMP_ID = "_id";
    public static final String COL_EMP_NAME = "emp_name";
    public static final String COL_EMP_GENDER = "emp_gender";
    public static final String COL_EMP_DEPT_ID = "dept_id";

    public static final String TABLE_NAME_DEPT = "department";

    public static final String COL_DEPT_ID = "_id";
    public static final String COL_DEPT_NAME = "emp_name";

    public static final String TABLE_CREATE_DEPT = "create table " + TABLE_NAME_DEPT + " ( " +
            COL_DEPT_ID + " integer primary key autoincrement, " +
            COL_DEPT_NAME + " text not null " +
            " ) ";

    public static final String TABLE_CREATE_EMP = "create table " + TABLE_NAME_EMP + " ( " +
            COL_EMP_ID + " integer primary key autoincrement, " +
            COL_EMP_NAME + " text not null, " +
            COL_EMP_GENDER + " text not null, " +
            COL_EMP_DEPT_ID + " integer not null, " +
            " foreign key (" + COL_EMP_DEPT_ID + ") references " + TABLE_NAME_DEPT + " (" + COL_DEPT_ID + ")" +
            " ) ";

    private static final String TABLE_DROP_EMP = "drop table if exists " + TABLE_NAME_EMP;
    private static final String TABLE_DROP_DEPT = "drop table if exists " + TABLE_NAME_DEPT;

    public DBClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("DBApp", TABLE_CREATE_DEPT);
        Log.d("DBApp", TABLE_CREATE_EMP);

        db.execSQL(TABLE_CREATE_DEPT);
        db.execSQL(TABLE_CREATE_EMP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_DROP_EMP);
        db.execSQL(TABLE_DROP_DEPT);

        onCreate(db);
    }
}
