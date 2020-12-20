package com.android.sqlite_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

        btnUpdate = findViewById(R.id.btnStudent_update);

        btnUpdate.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {

        SQLiteDatabase DB;

        @Override
        public void onClick(View v) {

            name = studentName.getText().toString();
            major = studentMajor.getText().toString();
            tel = studentTel.getText().toString();


            new AlertDialog.Builder(UpdateActivity.this)
                    .setTitle("알림")
                    .setMessage("정보 수정을 하시겠습니까?")
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            name = studentName.getText().toString();
                            major = studentMajor.getText().toString();
                            tel = studentTel.getText().toString();

                            if(name.length()==0|major.length()==0|tel.length()==0){
                                Toast.makeText(UpdateActivity.this,"모든 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
                            } else if (tel.length() != 11){
                                Toast.makeText(UpdateActivity.this,"양식에 맞는 전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                            } else{
                                try{
                                    DB = studentInfo.getWritableDatabase();

                                    String query = "UPDATE student SET studentname = '" + name + "', studentmajor = '" + major+ "', studenttel = '" + tel + "' WHERE studentid = " + studentId + ";";
                                    DB.execSQL(query);

                                    studentInfo.close();
                                    Toast.makeText(UpdateActivity.this, "학생 정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();

                                } catch (Exception e){
                                    e.printStackTrace();
                                    Toast.makeText(UpdateActivity.this, "오류가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                                }

                                //           Intent intent = new Intent(UpdateActivity.this, SelectActivity.class);
                                //           startActivity(intent);
                                finish();

                            }


                        }
                    })
                    .setNegativeButton("닫기", null)
                    .setCancelable(true)
                    .show();

        }
    };

    // 화면 터치 시 키보드 hide
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