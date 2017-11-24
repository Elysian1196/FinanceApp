package com.example.matthewspc.financeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void updateProfile(View view) //defines listener for the UpdateProfile Activity
    {
        startActivityForResult(new Intent(MainActivity.this, UpdateProfile.class), 1);
    }

    public void groupsButton(View view)//defines listener for the Groups Activity
    {
        //the listener doesn't do anything yet (the listener is defined in the XML file)
    }

    public void expensesButton(View view) //defines listener for the ExpensesTable Activity
    {
        Intent expensesTable = new Intent(MainActivity.this, ExpensesTable.class);
        startActivity(expensesTable);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //when we come back from the UpdateProfile activity
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                //this doesn't do anything yet
            }
        }
    }
}
