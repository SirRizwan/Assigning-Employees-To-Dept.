package com.example.rizwan.sqllite;


import android.os.Bundle;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import java.util.List;
import android.widget.Spinner;
/**
 * Created by Rizwan on 4/1/2018.
 */

public class Emp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    private EditText etEmpName;
    private RadioButton rdMale, rdFemale;
    Spinner spinner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        Button btnEmployee = (Button) findViewById(R.id.btnEmployee);
        final ListView lvEmployees = (ListView) findViewById(R.id.lvEmployees);
        etEmpName = (EditText) findViewById(R.id.etName);
        rdMale = (RadioButton) findViewById(R.id.rdMale);
        rdFemale = (RadioButton) findViewById(R.id.rdFemale);
        spinner = (Spinner) findViewById(R.id.spinner);

         spinner.setOnItemSelectedListener(this);


        loadSpinnerData();




        btnEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationDAL appDAL = new ApplicationDAL(getApplicationContext());

                appDAL.openDBConnection();

                //long deptId = appDAL.createDepartment("IT Department");

               // appDAL.createEmployee("Hasan", "Male", deptId);
                long abc = spinner.getId();
                //long deptId = appDAL.fetchAllDepartments();

                //  for(Department department : appDal1.fetchAllDepartments()){
                //       Log.d("DBApp", department.toString());
                //   }



                appDAL.createEmployee(etEmpName.getText().toString(), rdMale.isChecked() ? "Male" : "Female", abc);

                for(Department department : appDAL.fetchAllDepartments()){
                    Log.d("DBApp", department.toString());
                }

                List<Employee> employees = appDAL.fetchAllEmployees();

                for(Employee employee : employees){
                    Log.d("DBApp", employee.toString());
                }



                //ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, employees);

                //lvEmployees.setAdapter(arrayAdapter);

                //SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, appDAL.fetchEmployeesCursor(), new String[]{DBClass.COL_EMP_GENDER, DBClass.COL_EMP_NAME}, new int[] {android.R.id.text1}, CursorAdapter.FLAG_AUTO_REQUERY);

                //lvEmployees.setAdapter(cursorAdapter);

                SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.datarow, appDAL.fetchEmployeesCursor(), new String[]{DBClass.COL_EMP_ID, DBClass.COL_EMP_NAME, DBClass.COL_EMP_GENDER}, new int[] {R.id.tvEmpId, R.id.tvEmpName, R.id.tvEmpGender}, CursorAdapter.NO_SELECTION);

                lvEmployees.setAdapter(cursorAdapter);

                appDAL.closeDBConnection();
            }
        });

    }
    private void loadSpinnerData() {
        // database handler
        ApplicationDAL db = new ApplicationDAL(getApplicationContext());

        // Spinner Drop down elements
        List<Department> lables = db.fetchAllDepartments();

        // Creating adapter for spinner
        ArrayAdapter<Department> dataAdapter = new ArrayAdapter<Department>(this, android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



}
