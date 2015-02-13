package com.example.ben.sqlitedemo_contactlist;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class del_contact extends ActionBarActivity {

    Button delButton;
    View.OnClickListener delListener;
    TextView delStatusMsg;
    EditText deleteBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_contact);

        delListener = new View.OnClickListener() {
            public void onClick(View v) {
                String name = deleteBox.getText().toString();
                Cursor result = MainActivity.dbHelp.searchContact(name);

                if (result.getCount() <= 0) {
                    delStatusMsg.setText("Sorry, could not find that person!");
                } else {
                    //Run code to delete row from table
                    MainActivity.dbHelp.removeContact(name);
                    delStatusMsg.setText("Removed " + name + " from contacts.");
                }
            }
        };


        delButton = (Button)findViewById(R.id.delButton);
        delButton.setOnClickListener(delListener);

        delStatusMsg = (TextView) findViewById(R.id.delStatusMsg);
        deleteBox = (EditText)findViewById(R.id.editText6);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_del_contact, menu);
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
