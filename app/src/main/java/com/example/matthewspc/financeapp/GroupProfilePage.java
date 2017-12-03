package com.example.matthewspc.financeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GroupProfilePage extends AppCompatActivity implements View.OnClickListener{
    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_profile_page);
        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginPage.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        //displaying logged in user name
        final TextView userEmail = (TextView) findViewById(R.id.textViewUserEmail);
        userEmail.setText("Welcome "+user.getEmail());

        //adding listener to button
        buttonLogout.setOnClickListener(this);
    }
    public void joinGroup(View view) //defines listener for the Join Group Activity
    {
        startActivityForResult(new Intent(GroupProfilePage.this, JoinGroup.class), 1);
    }

    public void createGroup(View view)//defines listener for the Create Group Activity
    {
        //the listener  is defined in the XML file
        startActivityForResult(new Intent(GroupProfilePage.this, CreateGroup.class), 2);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //when we come back from the Join Group activity
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {

            }
        }
        else if (requestCode == 2){//or from the Create Group Activity
            if(resultCode == RESULT_OK) {

            }
        }
    }

    @Override
    public void onClick(View view) {
        //if logout is pressed
        if(view == buttonLogout){
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginPage.class));
        }
    }
}
