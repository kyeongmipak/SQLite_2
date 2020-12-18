package com.android.sqlite_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends Activity {
    Button btn_Insert;
    EditText studentname_isnert, studentmajor_isnert, studenttel_isnert;
    StudentInfo studentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        studentInfo = new StudentInfo(InsertActivity.this);

        btn_Insert = findViewById(R.id.btn_insert);

        btn_Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db;
                studentname_isnert = findViewById(R.id.studentname_insert);
                studentmajor_isnert = findViewById(R.id.studemajor_insert);
                studenttel_isnert = findViewById(R.id.studenttel_insert);
                String name = studentname_isnert.getText().toString();
                String major = studentmajor_isnert.getText().toString();
                String tel = studenttel_isnert.getText().toString();
                try {
                    db = studentInfo.getWritableDatabase();
                    String query = "INSERT INTO member (username, userid, passwd) VALUES ('"+name+"', '"+major+"', "+tel+");";
                    db.execSQL(query);

                    studentInfo.close();
                    Toast.makeText(InsertActivity.this,"Insert Ok!", Toast.LENGTH_SHORT).show();;

                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(InsertActivity.this,"Insert Erro!", Toast.LENGTH_SHORT).show();;
                }
            }
        });
    }
}