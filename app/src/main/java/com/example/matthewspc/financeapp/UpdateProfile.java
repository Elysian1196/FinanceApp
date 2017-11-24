package com.example.matthewspc.financeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        add();
        cancel();
    }
    public void add(){//this is the submission button method
        Button btn = (Button) findViewById(R.id.updateSubmitButton);
        final EditText spend_goal = (EditText) findViewById(R.id.goalSpendInput);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //here we send back anything needed to make the entry object
                Intent intent = new Intent();
                intent.putExtra("spendgoal", spend_goal.getText().toString());
                //Note that this does NOT work yet for the datepicker. Google how to get date values from the data picker.
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
    public void cancel(){//if we don't want to put anything in
        //we return nothing
        Button btn = (Button) findViewById(R.id.updateCancelButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
