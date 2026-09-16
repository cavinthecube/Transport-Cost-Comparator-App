package com.example.transportcostcomparator;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import android.widget.Toast;
import android.widget.DatePicker;



public class Input extends AppCompatActivity {

    Spinner category;
    EditText modeET;
    EditText distanceET;
    EditText costET;

    DatePicker startDp;
    DatePicker endDp;

    Button calcAndSave;
    Button clearBtn;
    String categoryChoice;
    DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input);

        // connecting EditTexts and Buttons to variables

        modeET = findViewById(R.id.modeET);
        distanceET = findViewById(R.id.distanceET);
        costET = findViewById(R.id.costET);
        calcAndSave = findViewById(R.id.calcBtn);
        clearBtn = findViewById(R.id.clearBtn);
        category = findViewById(R.id.categorySp);
        startDp = findViewById(R.id.startDP);
        endDp = findViewById(R.id.endDP);


        // creates database helper
        db = new DatabaseHelper(this);

        // Displaying categories in the spinner
        String [] categories = {"Private Vehicle", "Taxi", "Bus","Train","Motorcycle"};
        ArrayAdapter<String> n = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,categories);
        n.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        category.setAdapter(n);

        // gets selected category from spinner
        Spinner spinner = findViewById(R.id.categorySp);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryChoice = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        calcAndSave.setOnClickListener(v -> {
            Intent intent = new Intent(Input.this, Results.class);

            if (modeET.getText().toString().isEmpty() || distanceET.getText().toString().isEmpty()|| costET.getText().toString().isEmpty()){
                Toast.makeText(Input.this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean inserted;

            // Getting values from user input
            String mode = modeET.getText().toString();
            double distance = Double.parseDouble(distanceET.getText().toString());
            double cost = Double.parseDouble(costET.getText().toString());
            int startDay = startDp.getDayOfMonth();
            int startMonth = startDp.getMonth();
            int startYear = startDp.getYear();
            int endDay = endDp.getDayOfMonth();
            int endMonth = endDp.getMonth();
            int endYear = endDp.getYear();

            double travelDays = 0;

            if (startMonth != endMonth || startYear != endYear) {
                Toast.makeText(Input.this, "Please select dates in the same month", Toast.LENGTH_SHORT).show();
                return;
            } else if (endDay < startDay) {
                Toast.makeText(Input.this, "End date cannot be before start date", Toast.LENGTH_SHORT).show();
                return;
            }  else if (distance <= 0 || cost <= 0) {
                Toast.makeText(Input.this, "Values cannot be less than 0", Toast.LENGTH_SHORT).show();
                return;
            } else {

                // calculates travel days
                travelDays = (endDay - startDay) + 1;

                // storing input values in transport class variables
                Transport transport = new Transport(categoryChoice, mode, distance, travelDays, cost);
                TransportCalculator calculator = new TransportCalculator(transport);
                Recommendation recommendation = new Recommendation(transport);

                calculator.calcDailyTransportCost();
                calculator.calcMonthlyTransportCost();
                calculator.calcMonthlyTravelDist();

                inserted = db.insertTransport(transport.mode, transport.transChoice, calculator.dailyCost, calculator.monthlyCost, calculator.monthlyDistance, recommendation.costCategory(), recommendation.savingRec());

                if(inserted) {
                    Toast.makeText(Input.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(Input.this, "Failed to save", Toast.LENGTH_SHORT).show();
                }
            }


        });



        clearBtn.setOnClickListener(v -> {
            modeET.setText("");
            distanceET.setText("");
            costET.setText("");
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(Input.this, MainActivity.class);
            startActivity(intent);
            return true;

        }
        return true;
    }
}