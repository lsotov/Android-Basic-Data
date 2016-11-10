package com.example.lsoto.workingwithdatabasic;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class KeyValueData extends AppCompatActivity {

    private final String KEY_MESSAGE = "com.example.lsotov.MESSAGE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_value_data);

        setTextViewText();
    }

    public void setTextViewText(){
        SharedPreferences shp = getPreferencesHelper();
        String message = shp.getString(KEY_MESSAGE, "");

        if (message != null && !message.isEmpty()){
            TextView textView = (TextView) findViewById(R.id.keyvaluetext);
            textView.setText(message);
        }
    }

    public SharedPreferences getPreferencesHelper(){
        return getPreferences(Context.MODE_PRIVATE);
    }

    public void saveKeyValueTest(View view){
        SharedPreferences shp = getPreferencesHelper();
        SharedPreferences.Editor editor = shp.edit();
        editor.putString(KEY_MESSAGE, "Test");
        editor.commit();
    }

    public void readKeyValueTest(View view){
        setTextViewText();
    }
}
