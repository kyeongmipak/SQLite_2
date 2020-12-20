package com.android.sqlite_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectActivity extends Activity {
    private ArrayList<StudentBean> data = null;
    private StudentAdapter adapter = null;
    private ListView listView = null;
    private SQLiteDatabase DB;
    Button btnMain;
    ArrayAdapter<CharSequence> spinenerAdapter = null;
    private ArrayList<StudentBean> searchArr = null;
    Spinner spinner = null;
    EditText search_EdT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        spinenerAdapter = ArrayAdapter.createFromResource(this, R.array.searchSpinner, android.R.layout.simple_spinner_item);
        spinenerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = findViewById(R.id.search_spiner);
        spinner.setAdapter(spinenerAdapter);

        search_EdT = findViewById(R.id.search_ET);
        searchAll();

        searchArr = new ArrayList<StudentBean>();
        searchArr.addAll(data);
        search_EdT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = search_EdT.getText().toString();
                spinner = findViewById(R.id.search_spiner);
                String searchKind = spinner.getSelectedItem().toString();
                search(text, searchKind);
            }
        });


        findViewById(R.id.btnMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(SelectActivity.this, UpdateActivity.class);

                intent.putExtra("studentid", data.get(position).getStudentid());
                intent.putExtra("studentname", data.get(position).getStudentname());
                intent.putExtra("studentmajor", data.get(position).getStudentmajor());
                intent.putExtra("studenttel", data.get(position).getStudenttelno());

                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SelectActivity.this, DeleteActivity.class);

                intent.putExtra("studentid", data.get(position).getStudentid());
                intent.putExtra("studentname", data.get(position).getStudentname());
                intent.putExtra("studentmajor", data.get(position).getStudentmajor());
                intent.putExtra("studenttel", data.get(position).getStudenttelno());

                startActivity(intent);
                return true;
            }
        });
    } // onCreate 끝 ----------------------------------------------------


    @Override
    protected void onResume() {
        super.onResume();

        searchAll();

    } // onResume 끝 --------------------------------------------------------




    public void searchAll(){
        data = new ArrayList<StudentBean>();
        StudentInfo studentInfo;
        studentInfo = new StudentInfo(SelectActivity.this);
        try{                // 읽어올 때는 readable사용!
            DB = studentInfo.getReadableDatabase();
            // 차레대로 0, 1, 2 순서에 있음.
            String query = "SELECT * FROM student;";

            Cursor cursor =  DB.rawQuery(query, null);

            // String보다 퍼포먼스 빠른거!
          //  StringBuffer  stringBuffer = new StringBuffer();

            // 데이터를 하나씩 읽으면서 이동해라!
            while (cursor.moveToNext()){
                // 0, 1, 2에 맞게 불러와줘!
                int studentid = cursor.getInt(0);
                String studentname = cursor.getString(1);
                String studentmajor = cursor.getString(2);
                String studenttel = cursor.getString(3);

                // 불러온거를 stringBuffer에 담자!
                //stringBuffer.append("studentid: " + studentid + "\nstudentname : " + studentname + "\nstudentmajor : " + studentmajor + "\nstudenttel : " + studenttel + "\n----------------------------------");
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
    }

    public void search(String charText, String searchKind) {
        data.clear();
        if (charText.length() == 0) {
            data.addAll(searchArr);
        }
        else {
            switch (searchKind){
                case "학번":
                    for (int i = 0; i < searchArr.size(); i++) {
                        if (Integer.toString(searchArr.get(i).getStudentid()).contains(charText)) {
                            data.add(searchArr.get(i));
                        }
                    }
                    break;
                case "이름":
                    for (int i = 0; i < searchArr.size(); i++) {
                        if (searchArr.get(i).getStudentname().contains(charText)) {
                            data.add(searchArr.get(i));
                        }
                    }
                    break;
                case "전공":
                    for (int i = 0; i < searchArr.size(); i++) {
                        if (searchArr.get(i).getStudentmajor().contains(charText)) {
                            data.add(searchArr.get(i));
                        }
                    }
                    break;
                case "전화번호":
                    for (int i = 0; i < searchArr.size(); i++) {
                        if (searchArr.get(i).getStudenttelno().contains(charText)) {
                            data.add(searchArr.get(i));
                        }
                    }
                    break;
            }

        }
        adapter.notifyDataSetChanged();
    }

} // end ---------------------------------------------------------------