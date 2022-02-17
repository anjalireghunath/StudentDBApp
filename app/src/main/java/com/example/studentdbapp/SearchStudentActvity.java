package com.example.studentdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SearchStudentActvity extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4;
AppCompatButton b1,b2;
String getAdmission,getName,getRollNo,getCollege;
DatabaseHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student_actvity);
        dbhelper=new DatabaseHelper(this);
        dbhelper.getWritableDatabase();
        ed1=(EditText) findViewById(R.id.admision);
        ed2=(EditText) findViewById(R.id.name);
        ed3=(EditText) findViewById(R.id.roll);
        ed4=(EditText) findViewById(R.id.colege);
        b1=(AppCompatButton) findViewById(R.id.search);
        b2=(AppCompatButton) findViewById(R.id.backtom);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAdmission=ed1.getText().toString();
                Cursor c= dbhelper.searchData(getAdmission);
                if(c.getCount()==0) {
                   ed2.setText("");
                   ed3.setText("");
                   ed4.setText("");
                    Toast.makeText(getApplicationContext(), "Invalid Admission No", Toast.LENGTH_SHORT).show();
                }
                else{
                    while(c.moveToNext()){
                        getName=c.getString(1);
                        getRollNo=c.getString(2);
                        getCollege=c.getString(4);
                    }
                    ed2.setText(getName);
                    ed3.setText(getRollNo);
                    ed4.setText(getCollege);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}