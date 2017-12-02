package com.example.matthewspc.financeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddExpense extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        add();
        cancel();
    }
    public void add(){//this is the submission button method
        Button btn = (Button) findViewById(R.id.addExpenseSubmit);
        final EditText nameInput = (EditText) findViewById(R.id.NameInput);
        final EditText costInput = (EditText) findViewById(R.id.CostInput);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //here we send back anything needed to make the entry object
                Intent intent = new Intent();
                intent.putExtra("purchaseName", nameInput.getText().toString());
                intent.putExtra("purchaseCost", costInput.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }



    public void cancel(){//if we don't want to put anything in
        //we return nothing
        Button btn = (Button) findViewById(R.id.addExpenseCancel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
