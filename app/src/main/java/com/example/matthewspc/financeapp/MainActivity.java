package com.example.matthewspc.financeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private String spendGoal;
    private String spendDate;
    private String spendDaily;
    private String spendLeft;
    private TextView dateResult;
    private TextView spendResult;
    private TextView budjetResult;
    private TextView budjetLeftResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateResult=(TextView)this.findViewById(R.id.dateResult);
        spendResult=(TextView)this.findViewById(R.id.spendResult);
        budjetResult=(TextView)this.findViewById(R.id.budgetResult);
        budjetLeftResult=(TextView)this.findViewById(R.id.budgetLeftResult);
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
        startActivity(new Intent(MainActivity.this, ExpensesTable.class));
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //when we come back from the UpdateProfile activity
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                spendGoal = data.getStringExtra("spendGoal");//get extras
                spendDate = data.getStringExtra("spendDate");
                spendDaily = data.getStringExtra("spendDaily");
                spendLeft = data.getStringExtra("spendLeft");

                spendResult.setText(spendGoal);
                dateResult.setText(spendDate);
                budjetResult.setText(spendDaily);
                //customAdapter.expenseAdd(name,notes);//add expense to list
                //customAdapter.notifyDataSetChanged();//tells adapter to chanage listView
            }
        }
    }
}
