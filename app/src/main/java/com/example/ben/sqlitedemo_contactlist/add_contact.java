package com.example.ben.sqlitedemo_contactlist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class add_contact extends ActionBarActivity {

    View.OnClickListener addListener = null;
    Button addContactButton;
    EditText nameBox, phoneBox, emailBox, addrBox;
    TextView msgText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        addListener = new View.OnClickListener() {
            public void onClick(View v) {
                String name = nameBox.getText().toString();
                String phoneNum = phoneBox.getText().toString();
                String email = emailBox.getText().toString();
                String address = addrBox.getText().toString();
                MainActivity.dbHelp.insertContact(name, phoneNum, email, address);
                msgText.setText("Successfully added " + name + " to contacts!");
            }
        };

        //Add button and onClickListener
        addContactButton = (Button)findViewById(R.id.add_contact_button);
        addContactButton.setOnClickListener(addListener);

        nameBox = (EditText)findViewById(R.id.nameText);
        phoneBox = (EditText)findViewById(R.id.phoneText);
        emailBox = (EditText)findViewById(R.id.emailText);
        addrBox = (EditText)findViewById(R.id.addrText);



        msgText = (TextView)findViewById(R.id.statusMsgText);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
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
