package com.example.i.applicationtest.实验二;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.i.applicationtest.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.tablelayout); //表格布局
        setContentView(R.layout.constraintlayout);  //约束布局
        //setContentView(R.layout.activity_main);//垂直布局
    }
}
