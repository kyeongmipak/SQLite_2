package com.android.sqlite_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends Activity {
    Button btn_Insert;
    EditText studentname_isnert, studentmajor_isnert, studenttel_insert;
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
                studenttel_insert = findViewById(R.id.studenttel_insert);
                String name = studentname_isnert.getText().toString();
                String major = studentmajor_isnert.getText().toString();
                String tel = studenttel_insert.getText().toString();

                if(name.length()==0|major.length()==0|tel.length()==0){
                    Toast.makeText(InsertActivity.this,"모든 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else if (tel.length() != 11){
                    Toast.makeText(InsertActivity.this,"양식에 맞는 전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    new AlertDialog.Builder(InsertActivity.this)
                            .setTitle("입력을 완료하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SQLiteDatabase db;
                                    studentname_isnert = findViewById(R.id.studentname_insert);
                                    studentmajor_isnert = findViewById(R.id.studemajor_insert);
                                    studenttel_insert = findViewById(R.id.studenttel_insert);
                                    String name = studentname_isnert.getText().toString();
                                    String major = studentmajor_isnert.getText().toString();
                                    String tel = studenttel_insert.getText().toString();
                                    try {
                                        db = studentInfo.getWritableDatabase();
                                        String query = "INSERT INTO student (studentname, studentmajor, studenttel) VALUES ('"+name+"', '"+major+"', '"+tel+"');";
                                        db.execSQL(query);

                                        studentInfo.close();
                                        Toast.makeText(InsertActivity.this,"입력 완료!", Toast.LENGTH_SHORT).show();;
                                        Intent intent = new Intent(InsertActivity.this , SelectActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }catch (Exception e){
                                        e.printStackTrace();
                                        Toast.makeText(InsertActivity.this,"입력 실패!", Toast.LENGTH_SHORT).show();;
                                    }

                                }
                            })
                            .setNegativeButton("취소", null)
                            .setCancelable(true)
                            .show();
                }


            }
        });
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

}