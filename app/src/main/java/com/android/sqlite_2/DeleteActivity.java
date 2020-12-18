package com.android.sqlite_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteActivity extends Activity {

    TextView stid, stname, stmajor, sttel;
    Button btndel;
    StudentInfo studentInfo;
    SQLiteDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Intent intent = getIntent();
        int userid = intent.getIntExtra("studentid", 0);
        String studentname = intent.getStringExtra("studentname");
        String studentmajor = intent.getStringExtra("studentmajor");
        String studenttel = intent.getStringExtra("studenttel");



        stname = findViewById(R.id.studentname_delete);
        stmajor = findViewById(R.id.studemajor_delete);
        sttel = findViewById(R.id.studenttel_delete);

        stname.setText(studentname);
        stmajor.setText(studentmajor);
        sttel.setText(studenttel);


        btndel = findViewById(R.id.btnDelete);

        studentInfo = new StudentInfo(DeleteActivity.this);

        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(DeleteActivity.this)
                        .setTitle("정말 삭제 하시겠습니까?\n삭제한 후에는 복구되지 않습니다.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                try {
                                    DB = studentInfo.getWritableDatabase();
                                    String query = "DELETE FROM student WHERE studentid = '" + userid + "';";
                                    DB.execSQL(query);

                                    studentInfo.close();
                                    Toast.makeText(DeleteActivity.this, "DELETE OK!", Toast.LENGTH_SHORT).show();
                                    finish();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(DeleteActivity.this, "DELETE Error", Toast.LENGTH_SHORT).show();
                                }


                            }
                        })
                        .setNegativeButton("취소", null)
                        .setCancelable(true)
                        .show();
            }
        });


    }
}
