package com.android.sqlite_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends Activity {

    final static String TAG = "Status";

    Button btnUpdate;
    EditText studentName, studentMajor, studentTel;
    StudentInfo studentInfo;
    String name, major, tel;
    int studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        studentInfo = new StudentInfo(UpdateActivity.this);

        studentName = findViewById(R.id.studentname_update);
        studentMajor = findViewById(R.id.studentmajor_update);
        studentTel = findViewById(R.id.studenttel_update);

        Intent intent = getIntent();
        name = intent.getStringExtra("studentname");
        major = intent.getStringExtra("studentmajor");
        tel = intent.getStringExtra("studenttel");
        studentId = intent.getIntExtra("studentid",0);

        studentName.setText(name);
        studentMajor.setText(major);
        studentTel.setText(tel);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            SQLiteDatabase DB;
            @Override
            public void onClick(View v) {
                name = studentName.getText().toString();
                major = studentMajor.getText().toString();
                tel = studentTel.getText().toString();

                try{
                    DB = studentInfo.getWritableDatabase();

                    String query = "UPDATE student SET studentname = '" + name + "', studentmajor = '" + major+ "', studenttel = '" + tel + "' WHERE studentid = " + Integer.toString(studentId)+ ";";
                    DB.execSQL(query);

                    studentInfo.close();
                    Toast.makeText(UpdateActivity.this, "학생 정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();

                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(UpdateActivity.this, "오류가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                }

//            Intent intent = new Intent(UpdateActivity.this, SelectActivity.class);
//            startActivity(intent);
                finish();
            }
        });

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {

        SQLiteDatabase DB;

        @Override
        public void onClick(View v) {

            name = studentName.getText().toString();
            major = studentMajor.getText().toString();
            tel = studentTel.getText().toString();

            try{
                DB = studentInfo.getWritableDatabase();

                String query = "UPDATE student SET studentname = '" + name + "', studentmajor = '" + major+ "', studenttel = '" + tel + "' WHERE studentid = " + Integer.toString(studentId)+ ";";
                DB.execSQL(query);

                studentInfo.close();
                Toast.makeText(UpdateActivity.this, "학생 정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();

            } catch (Exception e){
                e.printStackTrace();
                Toast.makeText(UpdateActivity.this, "오류가 발생하였습니다.", Toast.LENGTH_SHORT).show();
            }

//            Intent intent = new Intent(UpdateActivity.this, SelectActivity.class);
//            startActivity(intent);
            finish();
        }
    };
}