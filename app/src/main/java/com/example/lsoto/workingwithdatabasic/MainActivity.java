package com.example.lsoto.workingwithdatabasic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Start Key Value Activity
    public void startKeyValue(View view){
        Intent intent = new Intent(this, KeyValueData.class);
        startActivity(intent);
    }

    public void startFile(View view){
        Intent intent = new Intent(this, FileData.class);
        startActivity(intent);
    }

    public void startSql(View view){
        Intent intent = new Intent(this, KeyValueData.class);
        startActivity(intent);
    }
}
