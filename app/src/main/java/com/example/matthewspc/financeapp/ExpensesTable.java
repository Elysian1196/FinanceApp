package com.example.matthewspc.financeapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.Calendar;
import java.util.Date;

public class ExpensesTable extends AppCompatActivity {

    ListView expensesList;
    public SQLiteDatabase database = new ExpensesHelper(this).getWritableDatabase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_table);
        addExpense();
        backButton();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //when we come back from the AddExpense activity
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                ContentValues values = new ContentValues();
                values.put(DatabaseContract.Expenses.COLUMN_PURCHASE_NAME, data.getStringExtra("purchaseName"));
                values.put(DatabaseContract.Expenses.COLUMN_PURCHASE_COST, data.getStringExtra("purchaseCost"));
                Date c = Calendar.getInstance().getTime();
                values.put(DatabaseContract.Expenses.COLUMN_PURCHASE_DATE, String.valueOf(c));
                long newRowId = database.insert(DatabaseContract.Expenses.TABLE_NAME, null, values);
            } if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }




    public void addExpense(){//sets up the button to go to the Add Expenses Activity. Works the same as in hw 2
        Button btn = (Button) findViewById(R.id.expensesAddButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(ExpensesTable.this, AddExpense.class), 1);
            }
        });
    }

    public void backButton(){ //sets up button to go back to main menu/profile page
        Button btn = (Button) findViewById(R.id.expenseBackButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExpensesTable.this, MainActivity.class));
            }
        });
    }
}
