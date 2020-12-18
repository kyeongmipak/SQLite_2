package com.android.sqlite_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectActivity extends Activity {
    final private String TAG = "Select";
    private ArrayList<StudentBean> data = null;
    private StudentAdapter adapter = null;
    private ListView listView = null;
    private SQLiteDatabase DB;
    Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        data = new ArrayList<StudentBean>();
        StudentInfo studentInfo;
        studentInfo = new StudentInfo(SelectActivity.this);
        try{                // 읽어올 때는 readable사용!
            DB = studentInfo.getReadableDatabase();
            // 차레대로 0, 1, 2 순서에 있음.
            String query = "SELECT * FROM student;";

            Cursor cursor =  DB.rawQuery(query, null);

            // String보다 퍼포먼스 빠른거!
            StringBuffer  stringBuffer = new StringBuffer();

            // 데이터를 하나씩 읽으면서 이동해라!
            while (cursor.moveToNext()){

                // 0, 1, 2에 맞게 불러와줘!
                int studentid = cursor.getInt(0);
                String studentname = cursor.getString(1);
                String studentmajor = cursor.getString(2);
                String studenttel = cursor.getString(3);
                Log.v(TAG, Integer.toString(studentid));
                // 불러온거를 stringBuffer에 담자!
//                stringBuffer.append("studentid: " + studentid + "\nstudentname : " + studentname + "\nstudentmajor : " + studentmajor + "\nstudenttel : " + studenttel + "\n----------------------------------");
                data.add(new StudentBean(studentid, studentname, studentmajor, studenttel));

            }
            adapter = new StudentAdapter(SelectActivity.this, R.layout.custom_layout, data);
            listView = findViewById(R.id.lv_student);
            listView.setAdapter(adapter);
            cursor.close();
            studentInfo.close();

        } catch(Exception e){
            e.printStackTrace();
        }

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Intent intent = new Intent(SelectActivity.this, UpdateActivity.class);
//
//                intent.putExtra("studentid", data.get(position).getStudentid());
//                intent.putExtra("studentname", data.get(position).getStudentname());
//                intent.putExtra("studentmajor", data.get(position).getStudentmajor());
//                intent.putExtra("studenttel", data.get(position).getStudenttelno());
//
//                startActivity(intent);
//            }
//        });
//
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(SelectActivity.this, UpdateActivity.class);
//
//                intent.putExtra("studentid", data.get(position).getStudentid());
//                intent.putExtra("studentname", data.get(position).getStudentname());
//                intent.putExtra("studentmajor", data.get(position).getStudentmajor());
//                intent.putExtra("studenttel", data.get(position).getStudenttelno());
//
//                startActivity(intent);
//                return true;
//            }
//        });
    } // onCreate 끝 ----------------------------------------------------


    @Override
    protected void onResume() {
        super.onResume();
//        onCreate(savedInstanceState);


//        StudentInfo studentInfo;
//        studentInfo = new StudentInfo(SelectActivity.this);
//        try{                // 읽어올 때는 readable사용!
//            DB = studentInfo.getReadableDatabase();
//            // 차레대로 0, 1, 2 순서에 있음.
//            String query = "SELECT * FROM student;";
//
//            Cursor cursor =  DB.rawQuery(query, null);
//
//            // String보다 퍼포먼스 빠른거!
//            StringBuffer  stringBuffer = new StringBuffer();
//
//            // 데이터를 하나씩 읽으면서 이동해라!
//            while (cursor.moveToNext()){
//                // 0, 1, 2에 맞게 불러와줘!
//                String studentid = cursor.getString(0);
//                String studentname = cursor.getString(1);
//                String studentmajor = cursor.getString(2);
//                String studenttel = cursor.getString(3);
//
//                // 불러온거를 stringBuffer에 담자!
//                //stringBuffer.append("studentid: " + studentid + "\nstudentname : " + studentname + "\nstudentmajor : " + studentmajor + "\nstudenttel : " + studenttel + "\n----------------------------------");
//                data.add(new StudentBean(studentid, studentname, studentmajor, studenttel));
//            }
//            listView = findViewById(R.id.lv_student);
//            listView.setAdapter(adapter);
//            cursor.close();
//            studentInfo.close();
//
//        } catch(Exception e){
//            e.printStackTrace();
//        }



    } // onResume 끝 --------------------------------------------------------






} // end ---------------------------------------------------------------