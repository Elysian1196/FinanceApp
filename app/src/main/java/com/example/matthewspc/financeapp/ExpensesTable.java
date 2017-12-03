package com.example.matthewspc.financeapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class ExpensesTable extends AppCompatActivity {

    ListView expensesList;
    public ExpensesHelper database = new ExpensesHelper(this);
    SimpleCursorAdapter customAdapter;
    Context context;
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
                ExpenseLogEntryData newBoi = new ExpenseLogEntryData(data.getStringExtra("purchaseName"), data.getStringExtra("purchaseCost"), data.getStringExtra("purchaseTag"));
                long uhOh = database.addExpense(newBoi);
            } if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }


    private void showExpenses() {
        Cursor cursor = database.getAllExpenses();//first we get a cursor
        if (cursor == null) {//and check if null, etc
            return;
        }
        if(cursor.getCount() == 0) {
            return;
        }
        final String[] columns = new String[] {database.KEY_NAME, database.KEY_COST, database.KEY_DATE, database.KEY_TAG};
        int[] boundTo = new int[] {R.id.NameT, R.id.CostT, R.id.DateT, R.id.TagT};
        customAdapter = new SimpleCursorAdapter(context, R.layout.expenses_layout, cursor, columns, boundTo, 0){
            //here we create a simpleCursor Adapter from the cursor
            @Override
            public void bindView(final View view, Context context, final Cursor cursor ) {//we still have to overwrite the bindview
                final int id_value = cursor.getInt(cursor.getColumnIndex("_id"));
                super.bindView(view, context, cursor);//we first call the super for most of the stuff
                Button delete = view.findViewById(R.id.DeleteThisT);//now we add the delete button
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        database.deleteExpense(id_value);
                        Cursor c = database.getAllExpenses();
                        customAdapter.swapCursor(c);
                        notifyDataSetChanged();
                        expensesList.setAdapter(customAdapter);
                    }
                });
            }
        };
        expensesList.setAdapter(customAdapter);
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
