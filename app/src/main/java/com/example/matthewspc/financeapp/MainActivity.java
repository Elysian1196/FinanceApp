package com.example.matthewspc.financeapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

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



    private float[] yData = {25.3f, 42.6f, 66.76f, 44,32f, 46.01f, 48.89f, 23.9f};
    public String[] xData = {"Groceries", "Dining Out","Rent","Utilities","Travel","Clothes","Other"};

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        budjetResult=(TextView)this.findViewById(R.id.budgetResult);
        budjetLeftResult=(TextView)this.findViewById(R.id.budgetLeftResult);
        Log.d("MainActivity", "onCreate: starting to create chart");
        pieChart = (PieChart) findViewById(R.id.idPieChart);

        //Created description object and set the text to that, may differ from yours but idk why?
        Description text = new Description();
        text.setText("Total budget: "+spendGoal);
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
        //the listener doesn't do anything yet (the listener is defined in the XML file)
        startActivity(new Intent(MainActivity.this, RegisterPage.class));
        //here is a test comment
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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                startActivity(new Intent(this, GroupProfilePage.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
