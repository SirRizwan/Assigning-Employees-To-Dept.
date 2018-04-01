package com.example.rizwan.sqllite;

/**
 * Created by Rizwan on 3/30/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class ApplicationDAL {

    private SQLiteDatabase database;
    private DBClass dbClass;
    private String[] allEmpCloumns = {DBClass.COL_EMP_ID, DBClass.COL_EMP_NAME, DBClass.COL_EMP_GENDER, DBClass.COL_EMP_DEPT_ID};
    private String[] allDeptCloumns = {DBClass.COL_DEPT_ID, DBClass.COL_DEPT_NAME};

    public ApplicationDAL(Context context) {
        this.dbClass = new DBClass(context);
    }

    public void openDBConnection() throws SQLException{
        database = dbClass.getWritableDatabase();
    }

    public void closeDBConnection() throws SQLException{
        database.close();
    }

    public long createDepartment(String deptName){

        ContentValues deptValues = new ContentValues();

        deptValues.put(DBClass.COL_DEPT_NAME, deptName);

        return database.insert(DBClass.TABLE_NAME_DEPT, null, deptValues);
    }

    public long createEmployee(String empName, String empGender, long deptId){

        ContentValues empValues = new ContentValues();

        empValues.put(DBClass.COL_EMP_NAME, empName);
        empValues.put(DBClass.COL_EMP_GENDER, empGender);
        empValues.put(DBClass.COL_EMP_DEPT_ID, deptId);

        return database.insert(DBClass.TABLE_NAME_EMP, null, empValues);
    }

    public List<Department> fetchAllDepartments(){

        List<Department> departments = new ArrayList<Department>();

        Cursor cursor = database.query(DBClass.TABLE_NAME_DEPT, allDeptCloumns, null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            departments.add(new Department(cursor.getInt(0), cursor.getString(1)));
            cursor.moveToNext();
        }

        return departments;
    }

    public List<Employee> fetchAllEmployees(){

        List<Employee> employees = new ArrayList<Employee>();

        Cursor cursor = database.query(DBClass.TABLE_NAME_EMP, allEmpCloumns, null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            employees.add(new Employee(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(0)));
            cursor.moveToNext();
        }

        return employees;
    }

    public Cursor fetchEmployeesCursor(){
        return database.query(DBClass.TABLE_NAME_EMP, allEmpCloumns, null, null, null, null, null);
    }

}
