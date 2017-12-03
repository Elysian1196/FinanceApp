package com.example.matthewspc.financeapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.EventLogTags;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.toIntExact;

public class MainActivity extends AppCompatActivity
{
    private String spendGoal;
    private String spendDate;
    private String spendLeft;
    private TextView goalDate;
    private TextView budget;
    private TextView budgetLeftResult;
    private ProfileDatabase profile;


    private float[] yData = {25.3f, 42.6f, 66.76f, 44,32f, 46.01f, 48.89f, 23.9f};
    private String[] xData = {"Groceries", "Dining Out","Rent","Utilities","Travel","Clothes","Other"};
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        budget=(TextView)this.findViewById(R.id.budget);
        goalDate=(TextView)this.findViewById(R.id.goalDate);
        budgetLeftResult=(TextView)this.findViewById(R.id.budgetLeftResult);
        Log.d("MainActivity", "onCreate: starting to create chart");
        pieChart = (PieChart) findViewById(R.id.idPieChart);
        profile = new ProfileDatabase(this);

        if (checkDatabase())
        {
            try {
                convertDatabase();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Description text = new Description();
        text.setText("Total budget: " + spendGoal);
        pieChart.setDescription(text);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setDrawEntryLabels(true);
        pieChart.setEntryLabelTextSize(20);

        addDataSet();
    }

    public void updateProfile(View view) //defines listener for the UpdateProfile Activity
    {
        startActivityForResult(new Intent(MainActivity.this, UpdateProfile.class), 1);
    }

    public void groupsButton(View view)//defines listener for the Groups Activity
    {
        startActivity(new Intent(MainActivity.this, RegisterPage.class));
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

                profile.updateProfile(spendGoal,spendDate);//adds new log with values
                try {
                    convertDatabase();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addDataSet() {
        Log.d("MainActivity", "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Expense Percentages");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(17);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(253,205,224));
        colors.add(Color.rgb(187,226,252));

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private Date convertDate(String date) throws ParseException //converts string to date object
    {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.parse(date);
    }

    private Date getDate() throws ParseException//gets the current date
    {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return date;
    }

    private int diffTime(Date today,Date goal)//finds the number of days between today and goal date
    {
        long diff = goal.getTime() - today.getTime();
        return toIntExact(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
    }

    private boolean checkDatabase()//checks if database has data
    {
        SQLiteDatabase db = profile.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + profile.DATABASE_TABLE +";", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if(count>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }



    private void convertDatabase() throws ParseException
    {//converts sqlite cursor to the textview
        Cursor cursor = profile.getLog();
        if (cursor.moveToFirst()) {
            spendGoal = cursor.getString(cursor.getColumnIndex(profile.GOAL));
            spendDate = cursor.getString(cursor.getColumnIndex(profile.DATE));
            double goal = Double.parseDouble(spendGoal);
            spendLeft = Double.toString(goal - 12);

            goalDate.setText(spendDate);
            budget.setText("$" + spendGoal);
            budgetLeftResult.setText("$" + spendLeft);
        }
    }
}
