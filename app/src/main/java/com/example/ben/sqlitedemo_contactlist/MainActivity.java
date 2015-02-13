package com.example.ben.sqlitedemo_contactlist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    // Buttons and their listeners
    Button addButton;
    Button delButton;
    Button queryButton;
    OnClickListener addListener = null;
    OnClickListener delListener = null;
    OnClickListener queryListener = null;

    public static DatabaseHelper dbHelp;
    //public static SQLiteDatabase db;

    public static class DatabaseHelper extends SQLiteOpenHelper{
        //Information about SQLite Database
        private static final String DB_NAME = "contactListDB.db";
        private static final int DB_VERSION = 1;
        private static final String TABLE_NAME = "contactList";
        private static final String CONTACT_TABLE_CREATE = "CREATE TABLE " +
                TABLE_NAME +
                " (Name VARCHAR, Phone VARCHAR, Email VARCHAR, Addr VARCHAR);";

        DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CONTACT_TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //No action to take
        }

        public void insertContact(String name, String phone, String email, String addr) {
            SQLiteDatabase db = dbHelp.getWritableDatabase();
            String sql_cmd = "INSERT INTO "
                    + TABLE_NAME
                    + " (Name, Phone, Email, Addr)"
                    + " VALUES ('" + name + "',"
                    + "'" + phone + "',"
                    + "'" + email + "',"
                    + "'" + addr + "');";
            db.execSQL(sql_cmd);
        }

        public Cursor searchContact(String name) {
            SQLiteDatabase db = dbHelp.getReadableDatabase();
            Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Name='" + name + "'", new String[] {});
            return cur;
        }

        public void removeContact(String name) {
            SQLiteDatabase db = dbHelp.getWritableDatabase();
            db.delete(TABLE_NAME, "Name=" + "'" + name + "'", new String []{});
        }

    }   //End DatabaseHelper class





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListener = new OnClickListener() {
            public void onClick(View v) {
                Intent addIntent = new Intent(MainActivity.this, add_contact.class);
                startActivity(addIntent);
            }
        };
        delListener = new OnClickListener() {
            public void onClick(View v) {
                Intent delIntent = new Intent(MainActivity.this, del_contact.class);
                startActivity(delIntent);
            }
        };
        queryListener = new OnClickListener() {
            public void onClick(View v) {
                Intent queryIntent = new Intent(MainActivity.this, query_contact.class);
                startActivity(queryIntent);
            }
        };

        //Add code to switch between activities
        addButton = (Button)findViewById(R.id.add_contact_button);
        addButton.setOnClickListener(addListener);
        delButton = (Button)findViewById(R.id.del_contact_button);
        delButton.setOnClickListener(delListener);
        queryButton = (Button)findViewById(R.id.query_contact_button);
        queryButton.setOnClickListener(queryListener);

        // Setup database
        dbHelp = new DatabaseHelper(this);
        //db = dbHelp.getWritableDatabase();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
