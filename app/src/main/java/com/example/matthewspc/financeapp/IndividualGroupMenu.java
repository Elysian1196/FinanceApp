package com.example.matthewspc.financeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.text.ParseException;

public class IndividualGroupMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_group_menu);
    }

    public void groupFunding(View view) //defines listener for the Add to Funding Activity
    {
        startActivityForResult(new Intent(IndividualGroupMenu.this, GroupFunding.class), 1);
    }

    public void back(View view)//defines listener for the back button
    {
        startActivity(new Intent(IndividualGroupMenu.this, GroupProfilePage.class));

    }
    public void finishProject(View view){//defines listener for finish project button
        //the listener is defined in the XML file. Currently does nothing.
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //when we come back from the Group Funding activity
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String donation = data.getStringExtra("amount");//get amount from funding page
            }
        }
    }
}
