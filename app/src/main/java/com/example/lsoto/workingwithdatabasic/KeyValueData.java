package com.example.lsoto.workingwithdatabasic;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/// If you have a relatively small collection of key-values that you'd like to save, you should use
// the SharedPreferences APIs. A SharedPreferences object points to a file containing key-value pairs
// and provides simple methods to read and write them.
// Each SharedPreferences file is managed by the framework and can be private or shared.

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
        TextView textView = (TextView) findViewById(R.id.keyvaluetext);

        if (message == null && message.isEmpty()){
            textView.setText("");
        } else {
            textView.setText(message);
        }
    }

    // Use this from an Activity if you need to use only one shared preference file for the activity.
    // Because this retrieves a default shared preference file that belongs to the activity,
    // you don't need to supply a name.
    public SharedPreferences getPreferencesHelper(){
        return getPreferences(Context.MODE_PRIVATE);
    }

    //  Use this if you need multiple shared preference files identified by name,
    // which you specify with the first parameter.
    public SharedPreferences getPreferencesHelper(String fileName){
        return getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public void saveKeyValueTest(View view){
        SharedPreferences shp = getPreferencesHelper();
        SharedPreferences.Editor editor = shp.edit();
        editor.putString(KEY_MESSAGE, "Test");
        editor.commit(); // Important! Call commit to save changes
        TextView textView = (TextView) findViewById(R.id.keyvaluetext);
        textView.setText("Test");
    }

    public void readKeyValueTest(View view){
        setTextViewText();
    }

    public void deleteKeyValueTest(View view){
        SharedPreferences shp = getPreferencesHelper();
        SharedPreferences.Editor editor = shp.edit();
        editor.remove(KEY_MESSAGE);
        editor.commit();
        TextView textView = (TextView) findViewById(R.id.keyvaluetext);
        textView.setText("");
    }
}
