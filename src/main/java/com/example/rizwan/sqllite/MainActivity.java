package com.example.rizwan.sqllite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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


public class MainActivity extends AppCompatActivity {
    private EditText etDept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept);
       // setContentView(R.layout.activity_main);


        Button btnDept = (Button) findViewById(R.id.btn_dept) ;

        Button btnNext = (Button) findViewById(R.id.btn_next);

        etDept = (EditText) findViewById(R.id.etdept);


        //lvEmployees.setBackgroundColor(Color.BLACK);

        btnNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                try {
                    startCameraOption(view);
                }
                catch (Exception ex){
                    Toast toast = Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });




        btnDept.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                String etcheckdept = etDept.getText().toString();

               if (etcheckdept.matches(" ")){
                   Toast toast = Toast.makeText(MainActivity.this,"Please Insert The Department Name",Toast.LENGTH_SHORT);
                   toast.show();return;

               }
                else {
                   ApplicationDAL appDal1 = new ApplicationDAL(getApplicationContext());

                   appDal1.openDBConnection();

                   long deptId = appDal1.createDepartment(etDept.getText().toString());

                   //  for(Department department : appDal1.fetchAllDepartments()){
                   //       Log.d("DBApp", department.toString());
                   //   }

                   Toast toast = Toast.makeText(MainActivity.this, "Department Added Succesfully", Toast.LENGTH_SHORT);
                   toast.show();
               }
            }
        });




    }
    public void startCameraOption(View view){
        Intent intent = new Intent(MainActivity.this, Emp.class);
        System.out.println("Starting next activity,");
        startActivity(intent);
    }

}
