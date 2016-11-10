package com.example.lsoto.workingwithdatabasic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

// Saving data to a database is ideal for repeating or structured data, such as contact information.
// One of the main principles of SQL databases is the schema: a formal declaration of how
// the database is organized.
// You may find it helpful to create a companion class, known as a contract class,
// which explicitly specifies the layout of your schema in a systematic and self-documenting way.
// A contract class is a container for constants that define names for URIs, tables, and columns.
public class SqlData extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
    }

    public void AccessDb(){
        Context context = getApplicationContext();
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(context);
    }
}
