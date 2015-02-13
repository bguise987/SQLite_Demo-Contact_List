package com.example.ben.sqlitedemo_contactlist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class query_contact extends ActionBarActivity {

    View.OnClickListener buttonListener = null;
    Button searchButton;
    EditText nameBox, nameDisp, phoneDisp, emailDisp, addrDisp;
    TextView msgText;

    SQLiteDatabase db = MainActivity.dbHelp.getReadableDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_contact);

        buttonListener = new View.OnClickListener() {
            public void onClick(View v) {
                String name = nameBox.getText().toString();
                Cursor result = MainActivity.dbHelp.searchContact(name);

                if (result.getCount() <= 0) {
                    msgText.setText("Sorry, could not find that person!");
                } else {
                    String[] colNames = result.getColumnNames();
                    result.moveToFirst();
                    msgText.setText("Found contact in address book!");
                    nameDisp.setText(result.getString(0));
                    phoneDisp.setText(result.getString(1));
                    emailDisp.setText(result.getString(2));
                    addrDisp.setText(result.getString(3));
                }
            }
        };

        //Add button and onClickListener
        searchButton = (Button)findViewById(R.id.button);
        searchButton.setOnClickListener(buttonListener);

        nameBox = (EditText)findViewById(R.id.editText);
        msgText = (TextView)findViewById(R.id.queryMsgText);

        nameDisp = (EditText)findViewById(R.id.editText2);
        phoneDisp = (EditText)findViewById(R.id.editText3);
        emailDisp = (EditText)findViewById(R.id.editText4);
        addrDisp = (EditText)findViewById(R.id.editText5);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_query_contact, menu);
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
