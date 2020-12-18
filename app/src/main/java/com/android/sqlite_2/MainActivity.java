package com.android.sqlite_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {


    Button insert;
    Button update;
    Button delete;
    Button select;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        select = findViewById(R.id.btnSelect);

        insert.setOnClickListener(mClickListener);
        update.setOnClickListener(mClickListener);
        delete.setOnClickListener(mClickListener);
        select.setOnClickListener(mClickListener);


    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {

                case R.id.btnInsert:
                    intent = new Intent(MainActivity.this, InsertActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btnUpdate:
                    intent = new Intent(MainActivity.this, UpdateActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btnDelete:
                    intent = new Intent(MainActivity.this, DeleteActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btnSelect:
                    intent = new Intent(MainActivity.this, SelectActivity.class);
                    startActivity(intent);
                    break;
//
                default:
            }
        }
    };
}